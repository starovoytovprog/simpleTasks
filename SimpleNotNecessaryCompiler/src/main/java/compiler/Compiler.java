package compiler;

import nodes.Node;
import nodes.NodeType;
import vm.VmCommands;

import java.util.*;

import static vm.Constants.COMMAND_LINE_DELIMITER;

/**
 * Компилятор
 * Строит машинный код из синтаксического дерева
 *
 * @author Starovoytov
 * @since 13.12.2017
 */
public class Compiler {
    private static final String BLOCK_INSERT_DELIMITER = "---BLOCK_";
    private static final String BACK_START = "---BACK_START_";
    private static final String BACK_INSERT_END = "---BACK_END_";

    private String mashineCodeString;
    private int blockCount;
    private Map<Integer, String> blocks;
    private List<String> codeBuffer;

    /**
     * Формирование машинного кода
     *
     * @param rootNode корень синтаксического дерева
     * @return машинный код
     */
    public String compile(Node rootNode) {
        mashineCodeString = "";
        blockCount = 0;
        blocks = new HashMap<>();
        codeBuffer = new ArrayList<>();
        nodeToString(rootNode);
        blocksToCode();
        backStartProcessing();
        return mashineCodeString;
    }

    /**
     * Обработка сформированных блоков кода, установка адресов начала блоков и возврата
     */
    private void backStartProcessing() {
        List<String> codeStrings = new ArrayList<>(Arrays.asList(mashineCodeString.split(COMMAND_LINE_DELIMITER)));
        Map<Integer, String> back = new HashMap<>();
        mashineCodeString = "";

        for (int i = 0; i < codeStrings.size(); i++) {
            if (codeStrings.get(i).startsWith(BACK_START)) {
                back.put(i + 1, BACK_INSERT_END + codeStrings.get(i).substring(codeStrings.get(i).lastIndexOf("_") + 1));
                codeStrings.set(i, "");
                i = 0;
            }
        }

        for (String code : codeStrings) {
            mashineCodeString += code;
            mashineCodeString += COMMAND_LINE_DELIMITER;
        }

        for (Integer i : back.keySet()) {
            mashineCodeString = mashineCodeString.replaceAll(back.get(i), i.toString());
        }
    }

    /**
     * Добавление блоков кода в общий
     */
    private void blocksToCode() {
        for (Integer id : blocks.keySet()) {
            mashineCodeString = mashineCodeString.replaceAll(BLOCK_INSERT_DELIMITER + id, nextCodeLineNumber(mashineCodeString) + "");
            mashineCodeString += COMMAND_LINE_DELIMITER;
            mashineCodeString += blocks.get(id);
        }
    }

    /**
     * Преобразование ноды в машинный код
     *
     * @param nextNode преобразуемая нода
     */
    private void nodeToString(Node nextNode) {
        if (nextNode == null)
            return;

        switch (nextNode.getType()) {
            case PRINT: {
                printNodeToString(nextNode);
                break;
            }
            case DIGIT: {
                digitNodeToString(nextNode);
                break;
            }
            case EOF: {
                eofNodeToString();
                break;
            }
            case SUM: {
                sumNodeToString(nextNode);
                break;
            }
            case MINUS: {
                minusNodeToString(nextNode);
                break;
            }
            case VARIABLE: {
                variableNodeToString(nextNode);
                break;
            }
            case IF: {
                ifNodeToString(nextNode);
                break;
            }
            case WHILE: {
                whileNodeToString(nextNode);
                break;
            }
        }
    }

    /**
     * Определения номера последней строки машинного кода
     *
     * @param code машинный код
     * @return номер строки
     */
    private int nextCodeLineNumber(String code) {
        return code.split(COMMAND_LINE_DELIMITER).length + 1;
    }

    /**
     * Формирование машинного кода из ноды условия
     *
     * @param expressionNode нода условия
     */
    private void printLogicExpressionNode(Node expressionNode) {
        switch (expressionNode.getType()) {
            case MORE_THAN: {
                nodeToString(expressionNode.getDependentNode(0));
                nodeToString(expressionNode.getDependentNode(1));
                break;
            }
            case LESS_THAN: {
                nodeToString(expressionNode.getDependentNode(1));
                nodeToString(expressionNode.getDependentNode(0));
                break;
            }
            case EQUALS: {
                nodeToString(expressionNode.getDependentNode(1));
                nodeToString(expressionNode.getDependentNode(0));
                break;
            }
        }

        if (expressionNode.getType() != NodeType.EQUALS) {
            mashineCodeString += VmCommands.LT;
        } else {
            mashineCodeString += VmCommands.EQ;
        }

        mashineCodeString += COMMAND_LINE_DELIMITER;
    }

    /**
     * Формирование машинного кода из ноды цикла
     *
     * @param whileNode нода цикла
     */
    private void whileNodeToString(Node whileNode) {
        mashineCodeString += BACK_START + ++blockCount;
        mashineCodeString += COMMAND_LINE_DELIMITER;

        printLogicExpressionNode(whileNode.getDependentNode(0));

        if (whileNode.getDependentNode(1) != null) {

            mashineCodeString += VmCommands.JNZ + " " + BLOCK_INSERT_DELIMITER + blockCount;
            mashineCodeString += COMMAND_LINE_DELIMITER;

            configureNewBlock(whileNode.getDependentNode(1), blockCount);
        }

        nodeToString(whileNode.getDependentNode(2));
    }

    /**
     * Формирование машинного кода из ноды условия
     *
     * @param ifNode нода условия
     */
    private void ifNodeToString(Node ifNode) {
        printLogicExpressionNode(ifNode.getDependentNode(0));

        if (ifNode.getDependentNode(1) != null) {
            mashineCodeString += VmCommands.JNZ + " " + BLOCK_INSERT_DELIMITER + ++blockCount;
            mashineCodeString += COMMAND_LINE_DELIMITER;
            mashineCodeString += BACK_START + blockCount;
            mashineCodeString += COMMAND_LINE_DELIMITER;

            configureNewBlock(ifNode.getDependentNode(1), blockCount);
        }

        if (ifNode.getDependentNode(2) != null) {
            mashineCodeString += VmCommands.JZ + " " + BLOCK_INSERT_DELIMITER + ++blockCount;
            mashineCodeString += COMMAND_LINE_DELIMITER;
            mashineCodeString += BACK_START + blockCount;
            mashineCodeString += COMMAND_LINE_DELIMITER;

            configureNewBlock(ifNode.getDependentNode(2), blockCount);
        }

        nodeToString(ifNode.getDependentNode(3));
    }

    /**
     * Формирование блока машинного кода
     *
     * @param blockNode   нода блока
     * @param blockNumber порядковый номер блока
     */
    private void configureNewBlock(Node blockNode, int blockNumber) {
        String blockCode;

        codeBuffer.add(mashineCodeString);
        mashineCodeString = "";
        nodeToString(blockNode);
        blockCode = mashineCodeString;
        mashineCodeString = codeBuffer.get(codeBuffer.size() - 1);
        codeBuffer.remove(codeBuffer.size() - 1);

        blockCode += VmCommands.JMP + " " + BACK_INSERT_END + blockNumber;

        blocks.put(blockNumber, blockCode);
    }

    /**
     * Формирование машинного кода из ноды переменной
     *
     * @param node нода переменной
     */
    private void variableNodeToString(Node node) {

        if (node.getDependentNode(0).getType() == NodeType.SET) {
            nodeToString(node.getDependentNode(1));
            mashineCodeString += VmCommands.STORE + " " + node.getValue();
            mashineCodeString += COMMAND_LINE_DELIMITER;
            nodeToString(node.getDependentNode(2));
        } else {
            mashineCodeString += VmCommands.FETCH + " " + node.getValue();
            mashineCodeString += COMMAND_LINE_DELIMITER;
        }
    }

    /**
     * Формирование машинного кода из ноды печати
     *
     * @param node нода печати
     */
    private void printNodeToString(Node node) {
        nodeToString(node.getDependentNode(0));
        mashineCodeString += VmCommands.ECHO;
        mashineCodeString += COMMAND_LINE_DELIMITER;
        nodeToString(node.getDependentNode(1));
    }

    /**
     * Формирование машинного кода из ноды числа
     *
     * @param node нода числа
     */
    private void digitNodeToString(Node node) {
        mashineCodeString += VmCommands.PUSH + " " + node.getValue() + COMMAND_LINE_DELIMITER;
    }

    /**
     * Формирование машинного кода завершения приложения
     */
    private void eofNodeToString() {
        mashineCodeString += VmCommands.HALT;
    }

    /**
     * Формирование машинного кода из ноды сложения
     *
     * @param node нода сложения
     */
    private void sumNodeToString(Node node) {
        nodeToString(node.getDependentNode(0));
        nodeToString(node.getDependentNode(1));
        mashineCodeString += VmCommands.ADD;
        mashineCodeString += COMMAND_LINE_DELIMITER;
    }

    /**
     * Формирование машинного кода из ноды вычитания
     *
     * @param node нода вычитания
     */
    private void minusNodeToString(Node node) {
        nodeToString(node.getDependentNode(1));
        nodeToString(node.getDependentNode(0));
        mashineCodeString += VmCommands.SUB;
        mashineCodeString += COMMAND_LINE_DELIMITER;
    }
}
