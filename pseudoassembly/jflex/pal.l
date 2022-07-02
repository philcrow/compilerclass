%%

%class PALexer
%standalone

%{
    StringBuffer string = new StringBuffer();

    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
}%

IDENT = [a-z]+
INT   = [0-9]+
FLOAT = [0-9]+ "." [0-9]+

%%

{IDENT} { System.out.println("ident: " + yytext()); }

[^] { System.out.println("rejected: " + yytext()); }
