#include <gtk/gtk.h>

static void
print_hello (GtkWidget *widget,
             gpointer   data)
{
  g_print ("Hello World\n");
}

static void
activate (GtkApplication *app,
          gpointer        user_data)
{
  GtkWidget *window;
  GtkWidget *grid;
  GtkWidget *button;
  GtkWidget *emptyLabel; // Nowy widget do utworzenia pustego pola

  /* Utwórz nowe okno i ustaw jego tytuł */
  window = gtk_application_window_new (app);
  gtk_window_set_title (GTK_WINDOW (window), "Okno");

  /* Tutaj tworzymy kontener, który spakuje nasze przyciski */
  grid = gtk_grid_new ();
  gtk_grid_set_row_homogeneous(GTK_GRID(grid), TRUE); // Ustawienie jednolitej wielkości wierszy
  gtk_grid_set_column_homogeneous(GTK_GRID(grid), TRUE); // Ustawienie jednolitej wielkości kolumn

  /* Umieść kontener w oknie */
  gtk_window_set_child (GTK_WINDOW (window), grid);

  // Utwórz puste pole jako GtkLabel
  emptyLabel = gtk_label_new("");
  gtk_grid_attach(GTK_GRID(grid), emptyLabel, 0, 0, 2, 1);

  button = gtk_button_new_with_label ("Przycisk 1");
  g_signal_connect (button, "clicked", G_CALLBACK (print_hello), NULL);
  gtk_widget_set_hexpand(button, TRUE);  // Spraw, aby przycisk był rozszerzalny w poziomie

  /* Umieść pierwszy przycisk w komórce siatki (0, 1) i spraw, aby wypełnił
   * cały wiersz w poziomie.
   */
  gtk_grid_attach (GTK_GRID (grid), button, 0, 1, 1, 1);

  button = gtk_button_new_with_label ("Przycisk 2");
  g_signal_connect (button, "clicked", G_CALLBACK (print_hello), NULL);
  gtk_widget_set_hexpand(button, TRUE);  // Spraw, aby przycisk był rozszerzalny w poziomie

  /* Umieść drugi przycisk w komórce siatki (1, 1) i spraw, aby wypełnił
   * cały wiersz w poziomie.
   */
  gtk_grid_attach (GTK_GRID (grid), button, 1, 1, 1, 1);

  button = gtk_button_new_with_label ("Wyjście");
  g_signal_connect_swapped (button, "clicked", G_CALLBACK (gtk_window_destroy), window);

  /* Umieść przycisk Wyjście w komórce siatki (0, 2) i spraw, aby wypełnił
   * dwie kolumny.
   */
  gtk_grid_attach (GTK_GRID (grid), button, 0, 2, 2, 1);

  gtk_window_present (GTK_WINDOW (window));
}

int
main (int    argc,
      char **argv)
{
  GtkApplication *app;
  int status;

  app = gtk_application_new ("org.gtk.example", G_APPLICATION_DEFAULT_FLAGS);
  g_signal_connect (app, "activate", G_CALLBACK (activate), NULL);
  status = g_application_run (G_APPLICATION (app), argc, argv);
  g_object_unref (app);

  return status;
}
