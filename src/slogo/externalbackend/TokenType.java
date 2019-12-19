package slogo.externalbackend;

import slogo.externalbackend.tokens.CommandToken;
import slogo.externalbackend.tokens.ConstantToken;
import slogo.externalbackend.tokens.Token;

/**
 * This is an enum of TokenTypes. It allows for simplification later to distinguish and compare the Tokens of various arguments.
 *
 * @author Jerry Wang
 */
public enum TokenType {

    ConstantToken(0),
    VariableToken(1),
    CommandToken(2),
    ListStartToken(3),
    ListEndToken(4),
    CommentToken(5),
    GroupStartToken(6),
    GroupEndToken(7),
    WhitespaceToken(8),
    NewlineToken(9);

    //This is used to determine token equality
    private final int tokenValue;

    /**
     * The TokenType constructor
     * @param value
     */
    TokenType(int value){
        tokenValue = value;
    }

    private int getTokenValue(){
        return this.tokenValue;
    }

    /**
     * @param otherToken
     * @return whether two token types are equal
     */
    public boolean equals(TokenType otherToken){
        return (this.tokenValue == otherToken.getTokenValue());
    }

}
