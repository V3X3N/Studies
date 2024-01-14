#include <gtk/gtk.h>

static void print_hello(GtkWidget *widget, gpointer data) {
    g_print("Hello World\n");
}

static void open_new_window(GtkWidget *widget, gpointer data) {
    GtkApplication *app = GTK_APPLICATION(data);

    // Sprawdź, czy to jest przycisk "Zapisz", jeśli tak, to nie otwieraj nowego okna
    const gchar *button_label = gtk_button_get_label(GTK_BUTTON(widget));
    if (g_strcmp0(button_label, "Zapisz") == 0) {
        g_print("Przycisk Zapisz nie otwiera nowego okna\n");
        return;
    }

    // Utwórz nowe okno i ustaw jego tytuł
    GtkWidget *new_window = gtk_application_window_new(app);
    gtk_window_set_title(GTK_WINDOW(new_window), "Nowe Okno");

    // Utwórz kontener
    GtkWidget *new_grid = gtk_grid_new();
    gtk_grid_set_row_homogeneous(GTK_GRID(new_grid), TRUE);
    gtk_grid_set_column_homogeneous(GTK_GRID(new_grid), TRUE);

    // Dodaj etykietę do nowego okna
    GtkWidget *label = gtk_label_new("To jest nowe okno!");
    gtk_widget_set_hexpand(label, TRUE);
    gtk_grid_attach(GTK_GRID(new_grid), label, 0, 0, 1, 1);

    gtk_window_set_child(GTK_WINDOW(new_window), new_grid);
    gtk_window_present(GTK_WINDOW(new_window));
}

static void save_data(GtkWidget *widget, gpointer data) {
    GtkEntryBuffer **buffers = (GtkEntryBuffer **)data;

    const gchar *imie = gtk_entry_buffer_get_text(buffers[0]);
    const gchar *nazwisko = gtk_entry_buffer_get_text(buffers[1]);
    const gchar *tytul = gtk_entry_buffer_get_text(buffers[2]);
    const gchar *cena = gtk_entry_buffer_get_text(buffers[3]);

    g_print("Zapisano dane:\n");
    g_print("Imię: %s\n", imie);
    g_print("Nazwisko: %s\n", nazwisko);
    g_print("Tytuł Książki: %s\n", tytul);
    g_print("Cena: %s\n", cena);
}

static void activate(GtkApplication *app, gpointer user_data) {
    GtkWidget *window;
    GtkWidget *grid;
    GtkWidget *button;
    GtkWidget *emptyLabel;
    GtkWidget *entry[4];
    GtkWidget *label[4];
    GtkEntryBuffer *buffer[4];

    window = gtk_application_window_new(app);
    gtk_window_set_title(GTK_WINDOW(window), "Okno");

    grid = gtk_grid_new();
    gtk_grid_set_row_homogeneous(GTK_GRID(grid), TRUE);
    gtk_grid_set_column_homogeneous(GTK_GRID(grid), TRUE);

    gtk_window_set_child(GTK_WINDOW(window), grid);

    for (int i = 0; i < 4; i++) {
        buffer[i] = gtk_entry_buffer_new(NULL, 0);
        entry[i] = gtk_entry_new_with_buffer(buffer[i]);
        gtk_widget_set_hexpand(entry[i], TRUE);
        gtk_grid_attach(GTK_GRID(grid), entry[i], 1, i, 1, 1);

        label[i] = gtk_label_new(NULL);
        const char *label_texts[] = {"Imię", "Nazwisko", "Tytuł Książki", "Cena"};
        gtk_label_set_text(GTK_LABEL(label[i]), label_texts[i]);
        gtk_grid_attach(GTK_GRID(grid), label[i], 0, i, 1, 1);
    }

    button = gtk_button_new_with_label("Zapisz");
    g_signal_connect(button, "clicked", G_CALLBACK(save_data), buffer);
    gtk_widget_set_hexpand(button, TRUE);
    gtk_grid_attach(GTK_GRID(grid), button, 0, 4, 2, 1);

    button = gtk_button_new_with_label("Przycisk 2");
    g_signal_connect(button, "clicked", G_CALLBACK(open_new_window), app);
    gtk_widget_set_hexpand(button, TRUE);
    gtk_grid_attach(GTK_GRID(grid), button, 0, 5, 2, 1);

    button = gtk_button_new_with_label("Przycisk 3");
    g_signal_connect(button, "clicked", G_CALLBACK(open_new_window), app);
    gtk_widget_set_hexpand(button, TRUE);
    gtk_grid_attach(GTK_GRID(grid), button, 0, 6, 2, 1);

    emptyLabel = gtk_label_new("");
    gtk_widget_set_hexpand(emptyLabel, TRUE);
    gtk_grid_attach(GTK_GRID(grid), emptyLabel, 0, 7, 2, 1);

    button = gtk_button_new_with_label("Quit");
    g_signal_connect_swapped(button, "clicked", G_CALLBACK(gtk_window_destroy), window);
    gtk_widget_set_hexpand(button, TRUE);
    gtk_grid_attach(GTK_GRID(grid), button, 0, 8, 2, 1);

    gtk_window_present(GTK_WINDOW(window));
}

int main(int argc, char **argv) {
    GtkApplication *app;
    int status;

    app = gtk_application_new("org.gtk.example", G_APPLICATION_DEFAULT_FLAGS);
    g_signal_connect(app, "activate", G_CALLBACK(activate), NULL);
    status = g_application_run(G_APPLICATION(app), argc, argv);
    g_object_unref(app);

    return status;
}
