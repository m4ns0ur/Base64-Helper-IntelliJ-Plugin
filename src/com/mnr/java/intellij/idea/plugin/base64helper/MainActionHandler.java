package com.mnr.java.intellij.idea.plugin.base64helper;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorModificationUtil;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.popup.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author m.rahimi
 */
public class MainActionHandler extends EditorAction {

    public static final String POPUP_MENU_TITLE = "Base64 Helper";
    public static final String ENCODER_SEPARATOR_TITLE = "String";
    public static final String HEX_ENCODER_SEPARATOR_STRING = "Hex String";

    protected MainActionHandler() {
        super(null);
        setupHandler(getEditorActionHandler());
    }

    private EditorActionHandler getEditorActionHandler() {
        return new EditorActionHandler() {
            @Override
            protected void doExecute(Editor editor, @Nullable Caret caret, DataContext dataContext) {
                ListPopup listPopup = getListPopup(editor);
                listPopup.showInBestPositionFor(editor);
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
        Project[] openProjects = ProjectManager.getInstance().getOpenProjects();

        if (openProjects.length <= 0) {
            return;
        }

        CommandProcessor.getInstance().executeCommand(openProjects[0], new Runnable() {
            @Override
            public void run() {
                ApplicationManager.getApplication().runWriteAction(new Runnable() {
                    @Override
                    public void run() {
                        if (editor == null || !editor.getDocument().isWritable()) {
                            return;
                        }

                        final String selectedText = getEditorSelectedText(editor);

                        if ((selectedText != null) && !selectedText.isEmpty()) {
                            String encodeDecode = popupItem.encodeDecode(selectedText);

                            if (encodeDecode != null) {
                                encodeDecode = encodeDecode.replace('\r', '\0');
                                EditorModificationUtil.insertStringAtCaret(editor, encodeDecode, true, true);
                            }
                        }
                    }
                });
            }
        }, "Base64 Converter", null);
    }

    private List<PopupItem> getPopupItems() {
        List<PopupItem> popupItemList = new ArrayList<PopupItem>();
        popupItemList.add(new EncoderPopupItem());
        popupItemList.add(new DecoderPopupItem());
        popupItemList.add(new HexEncoderPopupItem());
        popupItemList.add(new HexDecoderPopupItem());
        return popupItemList;
    }

    private String getEditorSelectedText(Editor editor) {
        SelectionModel selectionModel = editor.getSelectionModel();
        return selectionModel.getSelectedText();
    }
}
