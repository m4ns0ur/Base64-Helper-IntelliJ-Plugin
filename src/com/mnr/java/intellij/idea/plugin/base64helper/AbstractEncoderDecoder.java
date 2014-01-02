package com.mnr.java.intellij.idea.plugin.base64helper;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorModificationUtil;
import com.intellij.openapi.editor.SelectionModel;

/**
 * @author mnr
 */
public abstract class AbstractEncoderDecoder extends AnAction {
    public static final String EDITOR = "editor";

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        DataContext dataContext = anActionEvent.getDataContext();
        final Editor editor = getEditor(dataContext);

        if (editor == null) {
            return;
        }

        final String selectedText = getEditorSelectedText(editor);

        if ((selectedText != null) && !selectedText.isEmpty()) {
            Application application = ApplicationManager.getApplication();

            if (application == null) {
                return;
            }

            application.runWriteAction(new Runnable() {
                @Override
                public void run() {
                    String encodeDecode = encodeDecode(selectedText);

                    if (encodeDecode != null) {
                        EditorModificationUtil.insertStringAtCaret(editor, encodeDecode, true, true);
                    }
                }
            });
        }
    }

    public abstract String encodeDecode(String selectedText);

    private String getEditorSelectedText(Editor editor) {
        if (editor == null) {
            return null;
        }

        SelectionModel selectionModel = editor.getSelectionModel();
        return selectionModel.getSelectedText();
    }

    private Editor getEditor(DataContext dataContext) {
        return (Editor) dataContext.getData(EDITOR);
    }

    @Override
    public void update(AnActionEvent anActionEvent) {
        Presentation presentation = anActionEvent.getPresentation();
        DataContext dataContext = anActionEvent.getDataContext();
        Editor editor = getEditor(dataContext);
        String selectedText = getEditorSelectedText(editor);
        boolean enabled = checkEnableCondition(selectedText);
        presentation.setEnabled(enabled);
    }

    public boolean checkEnableCondition(String selectedText) {
        return (selectedText != null) && !selectedText.isEmpty();
    }
}
