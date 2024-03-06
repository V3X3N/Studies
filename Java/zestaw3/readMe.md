# Currency Converter

## Description
The "Currency Converter" project is a simple calculator that allows converting an amount from Polish złoty (PLN) to three other currencies: US dollar (USD), euro (EUR), and British pound (GBP). The program enables the user to select the target currency, enter the amount in PLN, and then performs the conversion based on current exchange rates.

## Classes

### CurrencyConverter
The main class of the project, containing the `main` method, which handles user interaction and currency conversion logic.

Methods:
- `main(String[] args)`: Entry point of the program, handling conversion logic.

### Description of Classes in the Project:
There are no additional classes in this project. The conversion logic is directly implemented in the `CurrencyConverter` class.

## Usage Instructions
1. Run the program.
2. Choose the target currency by entering the corresponding number.
3. Enter the amount in Polish złoty (PLN) to convert.
4. The program will display the converted amount in the chosen currency.

Example:
```
Choose the currency:
1. USD (US Dollar)
2. EUR (Euro)
3. GBP (British Pound)
Your choice: 2
Enter the amount in PLN PLN: 100
Converted amount: 100.0 PLN to EUR: 23.26
```

## Additional Notes
- Currency exchange rates are defined in the source code and can be adjusted according to current values.
- The program supports three major currencies but can be extended to support additional currencies.
- In case of entering an incorrect value (e.g., incorrect currency choice or invalid amount), the program will inform the user about the error.
