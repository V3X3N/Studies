#include <gtk/gtk.h>

typedef struct {
    char *imie;
    char *nazwisko;
    char *tytul;
    float cena;
    int ilosc;
} Ksiazka;

GList *lista_ksiazek = NULL;

GtkWidget *wprowadzenie[5];

static void zapisz_dane(GtkWidget *widget, gpointer data) {
    const char *imie = gtk_entry_buffer_get_text(gtk_entry_get_buffer(GTK_ENTRY(wprowadzenie[0])));
    const char *nazwisko = gtk_entry_buffer_get_text(gtk_entry_get_buffer(GTK_ENTRY(wprowadzenie[1])));
    const char *tytul = gtk_entry_buffer_get_text(gtk_entry_get_buffer(GTK_ENTRY(wprowadzenie[2])));
    float cena = atof(gtk_entry_buffer_get_text(gtk_entry_get_buffer(GTK_ENTRY(wprowadzenie[3]))));
    int ilosc = atoi(gtk_entry_buffer_get_text(gtk_entry_get_buffer(GTK_ENTRY(wprowadzenie[4]))));

    Ksiazka *nowa_ksiazka = g_malloc(sizeof(Ksiazka));
    nowa_ksiazka->imie = g_strdup(imie);
    nowa_ksiazka->nazwisko = g_strdup(nazwisko);
    nowa_ksiazka->tytul = g_strdup(tytul);
    nowa_ksiazka->cena = cena;
    nowa_ksiazka->ilosc = ilosc;

    lista_ksiazek = g_list_append(lista_ksiazek, nowa_ksiazka);
}

static void wyswietl_liste(GtkWidget *widget, gpointer data) {
    GtkWidget *okno_lista = gtk_application_window_new(GTK_APPLICATION(data));
    gtk_window_set_title(GTK_WINDOW(okno_lista), "Lista Książek");

    GtkWidget *kontener_lista = gtk_box_new(GTK_ORIENTATION_VERTICAL, 5);
    gtk_widget_set_margin_start(kontener_lista, 10);
    gtk_widget_set_margin_end(kontener_lista, 10);
    gtk_widget_set_margin_top(kontener_lista, 10);
    gtk_widget_set_margin_bottom(kontener_lista, 10);

    GList *iter;
    for (iter = lista_ksiazek; iter != NULL; iter = g_list_next(iter)) {
        Ksiazka *ksiazka = (Ksiazka *)iter->data;
        char *info = g_strdup_printf("Imię: %s\nNazwisko: %s\nTytuł: %s\nIlość: %d\nCena: %.2f\n\n",
                                     ksiazka->imie, ksiazka->nazwisko, ksiazka->tytul, ksiazka->ilosc, ksiazka->cena);
        GtkWidget *etykieta_ksiazki = gtk_label_new(info);
        g_free(info);
        gtk_box_append(GTK_BOX(kontener_lista), etykieta_ksiazki);
    }

    gtk_window_set_child(GTK_WINDOW(okno_lista), kontener_lista);
    gtk_window_present(GTK_WINDOW(okno_lista));
}

static void aktywuj(GtkApplication *app, gpointer user_data) {
    GtkWidget *okno;
    GtkWidget *siatka;
    GtkWidget *przycisk;
    GtkWidget *etykieta[5];
    GtkEntryBuffer *bufor[5];

    okno = gtk_application_window_new(app);
    gtk_window_set_title(GTK_WINDOW(okno), "Okno");

    siatka = gtk_grid_new();
    gtk_grid_set_row_homogeneous(GTK_GRID(siatka), TRUE);
    gtk_grid_set_column_homogeneous(GTK_GRID(siatka), TRUE);

    gtk_window_set_child(GTK_WINDOW(okno), siatka);

    for (int i = 0; i < 5; i++) {
        bufor[i] = gtk_entry_buffer_new(NULL, 0);
        wprowadzenie[i] = gtk_entry_new_with_buffer(bufor[i]);
        gtk_widget_set_hexpand(wprowadzenie[i], TRUE);
        gtk_grid_attach(GTK_GRID(siatka), wprowadzenie[i], 1, i, 1, 1);

        etykieta[i] = gtk_label_new(NULL);
        const char *teksty_etykiet[] = {"Imię", "Nazwisko", "Tytuł", "Cena", "Ilość"};
        gtk_label_set_text(GTK_LABEL(etykieta[i]), teksty_etykiet[i]);
        gtk_grid_attach(GTK_GRID(siatka), etykieta[i], 0, i, 1, 1);
    }

    przycisk = gtk_button_new_with_label("Zapisz");
    g_signal_connect(przycisk, "clicked", G_CALLBACK(zapisz_dane), NULL);
    gtk_widget_set_hexpand(przycisk, TRUE);
    gtk_grid_attach(GTK_GRID(siatka), przycisk, 0, 5, 2, 1);

    przycisk = gtk_button_new_with_label("Wyświetl Listę");
    g_signal_connect(przycisk, "clicked", G_CALLBACK(wyswietl_liste), app);
    gtk_widget_set_hexpand(przycisk, TRUE);
    gtk_grid_attach(GTK_GRID(siatka), przycisk, 0, 6, 2, 1);

    przycisk = gtk_button_new_with_label("Przycisk 3");
    gtk_widget_set_hexpand(przycisk, TRUE);
    gtk_grid_attach(GTK_GRID(siatka), przycisk, 0, 7, 2, 1);

    GtkWidget *pusta_etykieta = gtk_label_new("");
    gtk_widget_set_hexpand(pusta_etykieta, TRUE);
    gtk_grid_attach(GTK_GRID(siatka), pusta_etykieta, 0, 8, 2, 1);

    przycisk = gtk_button_new_with_label("Quit");
    g_signal_connect_swapped(przycisk, "clicked", G_CALLBACK(gtk_window_destroy), okno);
    gtk_widget_set_hexpand(przycisk, TRUE);
    gtk_grid_attach(GTK_GRID(siatka), przycisk, 0, 9, 2, 1);

    gtk_window_present(GTK_WINDOW(okno));
}

int main(int argc, char **argv) {
    GtkApplication *aplikacja;
    int status;

    aplikacja = gtk_application_new("org.gtk.przyklad", G_APPLICATION_DEFAULT_FLAGS);
    g_signal_connect(aplikacja, "activate", G_CALLBACK(aktywuj), NULL);
    status = g_application_run(G_APPLICATION(aplikacja), argc, argv);
    g_object_unref(aplikacja);

    return status;
}
