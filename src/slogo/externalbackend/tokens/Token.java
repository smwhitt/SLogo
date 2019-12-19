package slogo.externalbackend.tokens;

import slogo.externalbackend.TokenType;

/**
 * This is an abstract Token class. The token classes have minimal functionality, and are primarily a relic of when CommandNodes
 * lacked full functionality. As of now, these tokens are responsible fpr tracking the token type of each argument.
 *
 * @author Jerry Wang
 */
public abstract class Token {

    protected TokenType tokenType;
    protected String tokenName;

    /**
     * Token Constructor
     */
    public Token(){
    }

    /**
     * @return the enum type of the token.
     */
    public TokenType getTokenType(){
        return tokenType;
    }

}
