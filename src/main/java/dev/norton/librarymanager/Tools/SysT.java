package dev.norton.librarymanager.Tools;


public class SysT {



    // ------------------------------------------------------------------------------

    // region Routines

    public enum Routine {
        Author,
        Book,
        Gender,
        Publisher,
        Saga;

        Routine(){

        }

        public static String GetVal(Routine cod){
            return switch (cod) {
                case Author -> "Autor";
                case Gender -> "Gênero";
                case Book -> "Livro";
                case Publisher -> "Publicadora";
                case Saga -> "Saga";
                default -> "Invalid Routine";
            };
        }

    }

    // endregion Routines

}

