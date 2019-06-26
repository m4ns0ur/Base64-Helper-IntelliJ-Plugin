/*
 * Copyright 2014 Mansour Rahimi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mnr.intellij.plugin.base64helper;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.ui.popup.ListPopupStep;
import com.intellij.openapi.ui.popup.ListSeparator;
import com.intellij.openapi.ui.popup.MnemonicNavigationFilter;
import com.intellij.openapi.ui.popup.PopupStep;
import com.intellij.openapi.ui.popup.SpeedSearchFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author m.rahimi
 */
public class MainActionHandler extends EditorAction {

    private static final String POPUP_MENU_TITLE = "Base64 Helper";
    private static final String ENCODER_SEPARATOR_TITLE = "String";
    private static final String HEX_ENCODER_SEPARATOR_STRING = "Hex String";


    protected MainActionHandler() {
        super(null);
        setupHandler(getEditorActionHandler());
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        final Project project = e.getProject();
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        e.getPresentation().setVisible(project != null && editor != null && editor.getSelectionModel().hasSelection());
    }

    private EditorActionHandler getEditorActionHandler() {
        return new EditorActionHandler() {

            @Override
            protected void doExecute(Editor editor, @Nullable Caret caret, DataContext dataContext) {
                if (editor != null && editor.getSelectionModel().hasSelection()) {
                    ListPopup listPopup = getListPopup(editor);
                    listPopup.showInBestPositionFor(editor);
                }
            }
        };
    }

    private ListPopup getListPopup(Editor editor) {
        JBPopupFactory instance = JBPopupFactory.getInstance();
        ListPopupStep listPopupStep = getStep(editor);
        return instance.createListPopup(listPopupStep);
    }

    private ListPopupStep getStep(final Editor editor) {
        return new ListPopupStep() {

            @NotNull
            @Override
            public List getValues() {
                return getPopupItems();
            }

            @Override
            public boolean isSelectable(Object o) {
                return ((PopupItem) o).isSelectable(getEditorSelectedText(editor));
            }

            @Nullable
            @Override
            public Icon getIconFor(Object o) {
                return null;
            }

            @NotNull
            @Override
            public String getTextFor(Object o) {
                return ((PopupItem) o).getText();
            }

            @Nullable
            @Override
            public ListSeparator getSeparatorAbove(Object o) {
                if (o instanceof EncoderPopupItem) {
                    return new ListSeparator(ENCODER_SEPARATOR_TITLE);
                } else if (o instanceof HexEncoderPopupItem) {
                    return new ListSeparator(HEX_ENCODER_SEPARATOR_STRING);
                }
                return null;
            }

            @Override
            public int getDefaultOptionIndex() {
                return 0;
            }

            @Nullable
            @Override
            public String getTitle() {
                return POPUP_MENU_TITLE;
            }

            @Nullable
            @Override
            public PopupStep onChosen(final Object o, boolean b) {
                processSelection((PopupItem) o, editor);
                return null;
            }

            @Override
            public boolean hasSubstep(Object o) {
                return false;
            }

            @Override
            public void canceled() {
            }

            @Override
            public boolean isMnemonicsNavigationEnabled() {
                return false;
            }

            @Nullable
            @Override
            public MnemonicNavigationFilter getMnemonicNavigationFilter() {
                return null;
            }

            @Override
            public boolean isSpeedSearchEnabled() {
                return false;
            }

            @Nullable
            @Override
            public SpeedSearchFilter getSpeedSearchFilter() {
                return null;
            }

            @Override
            public boolean isAutoSelectionEnabled() {
                return false;
            }

            @Nullable
            @Override
            public Runnable getFinalRunnable() {
                return null;
            }
        };
    }

    private void processSelection(final PopupItem popupItem, final Editor editor) {
        final Project[] openProjects = ProjectManager.getInstance().getOpenProjects();
        if (openProjects.length <= 0) {
            return;
        }

        final Document document = editor.getDocument();
        WriteCommandAction.runWriteCommandAction(openProjects[0], () -> {
            if (!document.isWritable()) {
                return;
            }

            final String selectedText = getEditorSelectedText(editor);
            if ((selectedText != null) && !selectedText.isEmpty()) {
                String encodeDecode = popupItem.encodeDecode(selectedText);
                if (encodeDecode != null) {
                    encodeDecode = encodeDecode.replace('\r', '\0');
                    final SelectionModel selectionModel = editor.getSelectionModel();
                    document.replaceString(selectionModel.getSelectionStart(), selectionModel.getSelectionEnd(), encodeDecode);
                }
            }
        });
    }

    private List<PopupItem> getPopupItems() {
        List<PopupItem> popupItemList = new ArrayList<>();
        popupItemList.add(new EncoderPopupItem());
        popupItemList.add(new DecoderPopupItem());
        popupItemList.add(new HexEncoderPopupItem());
        popupItemList.add(new HexDecoderPopupItem());
        return popupItemList;
    }

    private String getEditorSelectedText(Editor editor) {
        return editor.getSelectionModel().getSelectedText();
    }
}
