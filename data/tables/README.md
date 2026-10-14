# Data-table templates (239 baked tables)

The client loads its game balance from **239 baked data tables** (items, units,
skills, scenarios, battle maps, missions, shops, guild war, …). Their *values*
shipped in CDN asset bundles and are not in the APK, but their **schemas** and
their **display text** are recoverable. This folder holds an authoring template
for every table, pre-filled with the text the localization file can give us.

| File | What it is |
|---|---|
| `templates/<Table>.csv` | One template per table: the exact columns of the baked row class, in serialized order. Text columns are pre-filled where possible (one row per string). Helper columns start with `#`. |
| `templates/<Table>.strings.csv` | Every localization string attributed to that table, per column, with English, source line and confidence (84 tables). |
| `TABLES.md` | Index: all 239 tables, load order, column lists with C# types, pre-fill counts. |
| `schema.json` | Machine-readable schema: raw/refined class names + TypeDefIndex, columns (`name`, `type`, `kind`), pre-fill stats. |
| `localization_index.csv` | For every line of `Localization_0.txt`: the table and column it belongs to, and the confidence. |
| `load_order.json` | Order of `<Table>.Load` calls in `PermanentData.Reload` (recovered from `libil2cpp.so`). |
| `anchors.json` | Curated anchors (line → table.column) verified by reading the strings. |

Regenerate everything with `scripts/tables/build.sh` (Python 3 + numpy).

## How the schema was recovered

Every table follows the same pattern in `extracted/il2cpp/dump.cs`:

```
[Serializable] class _<Table> : ScriptableObject { bool isMatrix; string key; List<__<Row>> data; }   // baked asset
[Serializable] class __<Row> { …raw columns… ; CopyTo(<Row> dest) }                                   // template columns
class <Table> : PermanentBase { List<<Row>> data; RefineData(_<Table>); Load(ref <Table>) }           // runtime table
```

The 239 table names match the client's own `"****** [<Table>] Table data is
missing"` messages one to one. Column types: `int` 2,082, `string` 455,
`List<int>` 318, `List<string>` 19. The templates use the raw `__<Row>`
columns, so authored data can later be baked back into the original
ScriptableObject shape.

`kind` in `schema.json`: `text` = localized display text (stored as the Korean
source string, which is also the localization key), `asset` = resource name
(atlas, icon, prefab, sound, …), `number` / `list` = values to author.

## How the localization was attributed

`Localization_0.txt` (27,396 strings × 8 languages) is keyed by the Korean
text, not by ids. Its order is not random:

1. It is the **de-duplicated union of every table's text columns**, emitted
   **table by table in `PermanentData.Reload` order** and **column by column**
   inside each table (e.g. Mails: senders → titles → bodies; Items: all names,
   then all descriptions). A string already emitted earlier is skipped — e.g.
   "Cao Cao" is missing from `Units.name` because the sticker sets used it first.
2. After the last table come ~5,800 UI/code strings (line 21,607 onward).

`scripts/tables/align_localization.py` assigns each line to a table column with
a monotone dynamic program over string shape (names vs sentences), pinned by
the curated anchors. Confidence:

- **anchored** (17,216 strings, 80%) — between two verified anchors of the same
  column: Mails, shops, Items (2,486 names + 2,449 descriptions), Heros
  (battle cries, retreat lines, biographies), Units (names + courtesy names),
  unit trees, Jobs, friendships, ranks, Skills, lord/mayor effects, Passives,
  passive lists, range-change effects, BattleMaps, Towns, Storybooks, Scenarios
  (titles, places, eras, story text), achievements, missions, conditions,
  techs, tiles, StringDefines, loading tips, raids, runes, arena stages and
  dialogue, staff roll, guild-war corps …
- **inferred** (4,389 strings) — placed by the dynamic program between anchors;
  boundaries between two adjacent name-like columns may be off by a few rows.

## Using a template

- A pre-filled row has the Korean text in the table's primary text column
  (usually `name`) plus `#<column>_en`, `#loc_row` (line in
  `Localization_0.txt`) and `#confidence`. A second text column is filled in the
  same row only when its string count matches exactly; otherwise use the
  `.strings.csv` sidecar — de-duplication means descriptions don't line up with
  names one to one.
- Row order follows the original table order (minus de-duplicated strings).
- Ids and every number are blank: those values were in the lost bundles. Fill
  them from your own sources (original Excel sheets, an old device's cache, the
  1998 PC game for the classic subset, or new design).
- Drop the `#` helper columns before baking.

## Known gaps

- 155 tables have no localized text at all (pure numbers/ids), so their
  templates are header-only.
- `ArenaNPCPassives` is not loaded by `PermanentData.Reload` (loaded elsewhere),
  so it has no position in the order and no pre-fill.
- Lines 14–62 of Mails mix two title-like groups; `Mails.name` gets none.
- The alignment model is inferred from the data; it is consistent across the
  whole file, but the original exporter itself is not available.
