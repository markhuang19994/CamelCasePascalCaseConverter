import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.*;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @author MarkHuang
 * @version <ul>
 * <li>2018/7/25, MarkHuang,new
 * </ul>
 * @since 2018/7/25
 */
public class NameConvertAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if (null == editor) {
            return;
        }
        CaretModel caretModel = editor.getCaretModel();
        List<Caret> carets = caretModel.getAllCarets();

        Runnable runnable = () -> {
            for (Caret caret : carets) {
                String selectedText = caret.getSelectedText();
                if (StringUtils.isBlank(selectedText)) {
                    continue;
                }
                String resultText = ConvertUtil.isPascalCase(selectedText) ? ConvertUtil.toCamelCase(selectedText) : ConvertUtil.toPascalCase(selectedText);
                editor.getDocument().replaceString(caret.getSelectionStart(), caret.getSelectionEnd(), resultText);
            }
        };

        WriteCommandAction.runWriteCommandAction(e.getData(PlatformDataKeys.PROJECT), runnable);
    }
}
