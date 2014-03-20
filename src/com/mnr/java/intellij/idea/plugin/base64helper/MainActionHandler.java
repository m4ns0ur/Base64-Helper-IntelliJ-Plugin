package com.mnr.java.intellij.idea.plugin.base64helper;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;
import org.jetbrains.annotations.Nullable;

/**
 * @author m.rahimi
 */
public class MainActionHandler extends EditorAction {
    protected MainActionHandler() {
        super(null);
        setupHandler(getNewHandler());
    }

    private EditorWriteActionHandler getNewHandler() {
        return new EditorWriteActionHandler() {
            @Override
            public void executeWriteAction(Editor editor, @Nullable Caret caret, DataContext dataContext) {
                super.executeWriteAction(editor, caret, dataContext);


            }
        };
    }
}
