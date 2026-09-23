# Transaction Analysis System

A Java-based transaction processing application that analyzes client trading activity from a CSV file. It calculates per-client BUY/SELL totals and net amounts, and produces a full transaction log sorted by timestamp.

## Features

- **CSV Ingestion** — Reads transaction records (transaction ID, client ID, type, amount, timestamp) from a CSV file using `BufferedReader`.
- **Per-Client Aggregation** — Computes total BUY amount, total SELL amount, and net amount (Buy − Sell) for every client using `HashMap<String, Double>`.
- **Client Summary Report** — Prints a summary for each client, sorted alphabetically/numerically by client ID via a `TreeSet`.
- **Chronological Transaction Log** — Sorts all transactions ascending by timestamp using `Comparator.comparing(Transaction::getTimestamp)` and prints the full list.
- **Object-Oriented Design** — Encapsulates each transaction as a `Transaction` object with private fields and public getters.

## Tools & Concepts Used

- Java (core language)
- Object-Oriented Programming (encapsulation, classes)
- Collections Framework — `HashMap`, `ArrayList`, `TreeSet`
- `Comparator` for custom sorting
- File I/O — `BufferedReader`, `FileReader`
- Exception handling (`try-with-resources`, `catch`)

## Project Structure

```
TransactionAnalysis.java
├── class Transaction        # Data model for a single transaction
└── class TransactionAnalysis # Main class: reads CSV, aggregates, sorts, prints
```

## How It Works

1. **Read** the CSV file line by line, skipping the header row.
2. **Parse** each line into `transactionId`, `clientId`, `transactionType`, `amount`, and `timestamp`.
3. **Store** each row as a `Transaction` object in an `ArrayList`.
4. **Aggregate** BUY and SELL amounts per client into two separate `HashMap`s.
5. **Summarize** — for each unique client ID (collected via `TreeSet` for sorted order), print total buy, total sell, and net amount.
6. **Sort** the full transaction list by timestamp (ascending) and print each entry.
7. **Handle errors** gracefully via a try-with-resources block and a catch-all `Exception` handler.

## CSV Input Format

The input file is expected to have a header row followed by comma-separated values:

```
TransactionID,ClientID,TransactionType,Amount,Timestamp
T0001,C1,BUY,134,2025-01-01 10:00:00
T0002,C1,BUY,134,2025-01-01 11:00:01
T0003,C1,SELL,151,2025-01-01 12:00:02
...
```

- `TransactionType` is case-insensitive (`BUY` / `SELL`).
- `Amount` is parsed as a `double`.
- `Timestamp` is a plain string, sorted lexicographically (works correctly for `yyyy-MM-dd HH:mm:ss` format).

## Setup & Usage

1. Update the `filePath` variable in `main()` to point to your CSV file:
   ```java
   String filePath = "D:\\transactions_v1.csv";
   ```
2. Compile the program:
   ```bash
   javac TransactionAnalysis.java
   ```
3. Run it:
   ```bash
   java TransactionAnalysis
   ```

## Sample Output

```
Client Summary:
Client: C1 -> Total Buy Amount: 604.0, Total Sell Amount: 151.0, Net Amount: 453.0
Client: C2 -> Total Buy Amount: 725.0, Total Sell Amount: 455.0, Net Amount: 270.0
...

Transactions Sorted By Timestamp (Ascending):
2025-01-01 09:00:09 | T0010 | C2 | BUY | 270
2025-01-01 09:00:19 | T0020 | C4 | BUY | 440
2025-01-01 09:00:29 | T0030 | C6 | SELL | 610
...
```

## Possible Improvements

- Replace the hardcoded file path with a command-line argument.
- Parse timestamps as `LocalDateTime` for more robust chronological sorting.
- Add unit tests for parsing and aggregation logic.
- Export the summary and sorted log to output CSV/JSON files.
- Add input validation for malformed CSV rows.

## License

This project is provided as-is for educational and portfolio purposes.
