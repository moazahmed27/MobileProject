# Expense Tracker (FNI-421 Phase 1)

Compose + XML (mandatory part) + Room + Intents.

## Team
- **Saad** — Project setup, Room entities, DAO, Database
- **Youssef** — Repository, ViewModel (`StateFlow`), MainActivity & explicit Intent
- **Ashraf** — Compose Home Screen (`ExpenseScreen`) & `ExpenseCard`
- **Moaaz** — Compose `AddExpenseDialog`, `CategoryChipRow` & form validation
- **Gamil** — XML-in-Compose `SpendingBanner` (`view_banner.xml`), README & ACM Abstract
- **Shalaby** — Full XML `ExpenseDetailActivity` (`activity_expense_detail.xml`), edit/delete & implicit share Intent

## Run
Open the repository folder in Android Studio (Koala or newer), sync Gradle, and run (`minSdk 24`).

## Rubric Map
| Category | Where |
|---|---|
| Feature coverage (Compose, Intent) | `MainActivity.kt` (Compose + explicit Intent), `ui/detail/ExpenseDetailActivity.kt` (implicit `ACTION_SEND`) |
| XML part | `res/layout/activity_expense_detail.xml` (full screen) + `res/layout/view_banner.xml` (embedded in Compose via `AndroidView` in `ui/components/SpendingBanner.kt`) |
| UI/UX | Material 3, budget progress banner, empty state, input validation (`ui/screens/ExpenseScreen.kt`, `ui/components/AddExpenseDialog.kt`) |
| Code quality | Layered MVVM (`data/`, `ui/screens/`, `ui/components/`, `ui/detail/`), `val` over `var`, lambdas, state hoisting, data classes |
| Persistence | Room: `data/Expense.kt`, `data/Categories.kt`, `data/ExpenseDao.kt`, `data/AppDatabase.kt`, `data/ExpenseRepository.kt` |
| 2-page abstract | `abstract/abstract.tex` (ACM `acmart`, compile on Overleaf) |
