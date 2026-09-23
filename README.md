# 💹 Transaction Analysis System

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/Paradigm-OOP-4B8BBE?style=for-the-badge" alt="OOP"/>
  <img src="https://img.shields.io/badge/Collections-HashMap_%7C_TreeSet_%7C_ArrayList-6DB33F?style=for-the-badge" alt="Collections"/>
  <img src="https://img.shields.io/badge/License-Educational-blue?style=for-the-badge" alt="License"/>
</p>

A lightweight **Java console application** that ingests client trading activity from a CSV file, computes per-client BUY/SELL aggregates, and produces a full transaction log sorted chronologically — built using core Java, the Collections Framework, and clean object-oriented design.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tools & Concepts Used](#tools--concepts-used)
- [Project Structure](#project-structure)
- [How It Works](#how-it-works)
- [CSV Input Format](#csv-input-format)
- [Setup & Usage](#setup--usage)
- [Sample Output](#sample-output)
- [Possible Improvements](#possible-improvements)
- [License](#license)

---

## Overview

This project simulates a simplified back-office **transaction analysis pipeline**: raw trade records go in as a CSV file, and structured per-client financial summaries plus a chronological audit log come out. It's a self-contained demonstration of core Java fundamentals — file I/O, collections, sorting, encapsulation, and exception handling — applied to a realistic finance-domain problem.

---

## Features

| Feature | Description |
| :--- | :--- |
| 📥 **CSV Ingestion** | Reads transaction records (ID, client ID, type, amount, timestamp) from a CSV file using `BufferedReader` |
| 🧮 **Per-Client Aggregation** | Computes total BUY amount, total SELL amount, and net amount (Buy − Sell) for every client via `HashMap<String, Double>` |
| 📊 **Client Summary Report** | Prints a summary per client, sorted alphabetically/numerically by client ID using a `TreeSet` |
| 🕒 **Chronological Transaction Log** | Sorts all transactions ascending by timestamp with `Comparator.comparing(Transaction::getTimestamp)` |
| 🏗️ **Object-Oriented Design** | Encapsulates each transaction as a `Transaction` object with private fields and public getters |

---

## Tools & Concepts Used

- **Java** (core language)
- **Object-Oriented Programming** — encapsulation, classes
- **Collections Framework** — `HashMap`, `ArrayList`, `TreeSet`
- **`Comparator`** for custom sorting
- **File I/O** — `BufferedReader`, `FileReader`
- **Exception Handling** — `try-with-resources`, `catch`

---

## Project Structure

```
TransactionAnalysis.java
├── class Transaction          # Data model for a single transaction
└── class TransactionAnalysis  # Main class: reads CSV, aggregates, sorts, prints
```

---

## How It Works

1. **Read** the CSV file line by line, skipping the header row.
2. **Parse** each line into `transactionId`, `clientId`, `transactionType`, `amount`, and `timestamp`.
3. **Store** each row as a `Transaction` object in an `ArrayList`.
4. **Aggregate** BUY and SELL amounts per client into two separate `HashMap`s.
5. **Summarize** — for each unique client ID (collected via `TreeSet` for sorted order), print total buy, total sell, and net amount.
6. **Sort** the full transaction list by timestamp (ascending) and print each entry.
7. **Handle errors** gracefully via a try-with-resources block and a catch-all `Exception` handler.

---

## CSV Input Format

The input file must have a header row followed by comma-separated values:

```csv
TransactionID,ClientID,TransactionType,Amount,Timestamp
T0001,C1,BUY,134,2025-01-01 10:00:00
T0002,C1,BUY,134,2025-01-01 11:00:01
T0003,C1,SELL,151,2025-01-01 12:00:02
```

| Field | Notes |
| :--- | :--- |
| `TransactionType` | Case-insensitive (`BUY` / `SELL`) |
| `Amount` | Parsed as a `double` |
| `Timestamp` | Plain string, sorted lexicographically — correct for `yyyy-MM-dd HH:mm:ss` format |

---

## Setup & Usage

**1. Point the app at your CSV file**

Update the `filePath` variable in `main()`:

```java
String filePath = "D:\\transactions_v1.csv";
```

**2. Compile**

```bash
javac TransactionAnalysis.java
```

**3. Run**

```bash
java TransactionAnalysis
```

---

## Sample Output

```
Client Summary:
Client: C1 -> Total Buy Amount: 604.0, Total Sell Amount: 151.0, Net Amount: 453.0
Client: C2 -> Total Buy Amount: 725.0, Total Sell Amount: 455.0, Net Amount: 270.0
...

Transactions Sorted By Timestamp (Ascending):
2025-01-01 09:00:09 | T0010 | C2 | BUY  | 270
2025-01-01 09:00:19 | T0020 | C4 | BUY  | 440
2025-01-01 09:00:29 | T0030 | C6 | SELL | 610
...
```

---

## Possible Improvements

- [ ] Replace the hardcoded file path with a command-line argument
- [ ] Parse timestamps as `LocalDateTime` for more robust chronological sorting
- [ ] Add unit tests for parsing and aggregation logic
- [ ] Export the summary and sorted log to output CSV/JSON files
- [ ] Add input validation for malformed CSV rows

---

## License

This project is provided **as-is** for educational and portfolio purposes.
