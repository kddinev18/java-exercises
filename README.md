# Java Exercises

Solutions for all nine Word exercise files, ordered by ascending modification time.
The package spelling `excerciseN` intentionally follows the assignment wording.

| Package | Source Word file | Modified | Covered tasks |
|---|---|---:|---:|
| `excercise1` | `Задачи за упражнение.docx` | 2026-09-10 13:11:51 | 1-16 |
| `excercise2` | `javaarrays.docx` | 2026-09-10 13:13:25 | 1-10 |
| `excercise3` | `ArraList.docx` | 2026-09-10 13:13:29 | 1-6 |
| `excercise4` | `javaclasses.docx` | 2026-09-10 13:13:33 | 1-8 |
| `excercise5` | `javaconstr.docx` | 2026-09-10 13:13:36 | 1-11 |
| `excercise6` | `javamethods.docx` | 2026-09-10 13:13:40 | 1-8, 10 |
| `excercise7` | `javainheritance.docx` | 2026-09-10 13:14:03 | 1-4 |
| `excercise8` | `javamultiinherianceexercise.docx` | 2026-09-10 13:14:06 | 1-3 |
| `excercise9` | `javafile.docx` | 2026-09-10 13:14:12 | 1-3 |

Each package contains one public class matching its document. The numbered methods and nested model
classes implement every numbered task from that document. The `main` method in each class gives a
small runnable demonstration.

## Compile and run

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse src/main/java -Filter *.java).FullName
java -cp out excercise1.Exercise1
java -cp out excercise2.Exercise2
java -cp out excercise3.Exercise3
java -cp out excercise4.Exercise4
java -cp out excercise5.Exercise5
java -cp out excercise6.Exercise6
java -cp out excercise7.Exercise7
java -cp out excercise8.Exercise8
java -cp out excercise9.Exercise9
```

Run all deterministic checks with:

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse src/main/java,src/test/java -Filter *.java).FullName
java -ea -cp out ProjectChecks
```
