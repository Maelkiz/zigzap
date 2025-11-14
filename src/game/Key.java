package game;

 public enum Key {
    // Alphabet
    A(65), B(66), C(67), D(68), E(69), F(70), G(71),
    H(72), I(73), J(74), K(75), L(76), M(77), N(78),
    O(79), P(80), Q(81), R(82), S(83), T(84), U(85),
    V(86), W(87), X(88), Y(89), Z(90),

    // Numbers (Top row)
    DIGIT_0(48), DIGIT_1(49), DIGIT_2(50), DIGIT_3(51), DIGIT_4(52),
    DIGIT_5(53), DIGIT_6(54), DIGIT_7(55), DIGIT_8(56), DIGIT_9(57),

    // Punctuation / Symbols
    SPACE(32), ENTER(13), TAB(9), BACKSPACE(8),
    SHIFT(16), CTRL(17), ALT(18),
    CAPS_LOCK(20), ESCAPE(27),
    LEFT_ARROW(37), UP_ARROW(38), RIGHT_ARROW(39), DOWN_ARROW(40),
    DELETE(46), INSERT(45),
    HOME(36), END(35), PAGE_UP(33), PAGE_DOWN(34),

    // Punctuation keys
    SEMICOLON(186), EQUALS(187), COMMA(188), DASH(189),
    PERIOD(190), FORWARD_SLASH(191), BACKTICK(192),
    OPEN_BRACKET(219), BACKSLASH(220), CLOSE_BRACKET(221), QUOTE(222),

    // Function keys
    F1(112), F2(113), F3(114), F4(115), F5(116), F6(117),
    F7(118), F8(119), F9(120), F10(121), F11(122), F12(123);

    private final int keyCode;

    Key(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
