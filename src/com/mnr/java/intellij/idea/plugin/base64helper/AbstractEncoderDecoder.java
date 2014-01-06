package com.mnr.java.intellij.idea.plugin.base64helper;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorModificationUtil;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;

/**
 * @author mnr
 */
public abstract class AbstractEncoderDecoder extends EditorAction {
    protected AbstractEncoderDecoder() {
        super(null);

        setupHandler(new EditorWriteActionHandler() {
            @Override
            public void executeWriteAction(Editor editor, DataContext dataContext) {
                if (editor == null) {
                    return;
                }

                final String selectedText = getEditorSelectedText(editor);

                if ((selectedText != null) && !selectedText.isEmpty()) {
                    String encodeDecode = encodeDecode(selectedText);

                    if (encodeDecode != null) {
                        EditorModificationUtil.insertStringAtCaret(editor, encodeDecode, true, true);
                    }
                }
            }
        });
    }

    public abstract String encodeDecode(String selectedText);

    private String getEditorSelectedText(Editor editor) {
        SelectionModel selectionModel = editor.getSelectionModel();
        return selectionModel.getSelectedText();
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
