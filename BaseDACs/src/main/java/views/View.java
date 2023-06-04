package views;

import lombok.Setter;

public abstract class View {
    @Setter
    protected static boolean isAdmin = false;
    @Setter
    protected static int id;
}
