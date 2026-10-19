// Determinism guard for packages/rules, content and campaign (ADR 0004, ADR 0006).
// Biome's noRestrictedGlobals bans clock/IO globals; this catches what it can't:
// property calls and float literals. Golden-replay tests are the real proof.
import { readdirSync, readFileSync, statSync } from "node:fs";
import { join, relative } from "node:path";

const ROOT = new URL("..", import.meta.url).pathname;
const SOURCES = ["packages/rules/src", "packages/content/src", "packages/campaign/src"].map((p) =>
  join(ROOT, p),
);

const BANNED = [
  [/\bMath\.random\b/, "use the Prng carried in the state"],
  [/\.localeCompare\s*\(/, "locale-dependent ordering; compare code units"],
  [/\.toLocale\w*\s*\(/, "locale-dependent formatting"],
  [/(?<![\w.])\d+\.\d+(?![\w.])/, "float literal; use fixed-point integers (x1000)"],
];

function files(dir) {
  return readdirSync(dir).flatMap((name) => {
    const p = join(dir, name);
    return statSync(p).isDirectory() ? files(p) : p.endsWith(".ts") ? [p] : [];
  });
}

// Blank out comments and string literals so they can't trigger or hide matches.
function codeOnly(text) {
  return text.replace(/\/\*[\s\S]*?\*\/|\/\/[^\n]*|(["'`])(?:\\.|(?!\1)[^\\])*\1/g, (m) =>
    m.replace(/[^\n]/g, " "),
  );
}

let failures = 0;
for (const file of SOURCES.flatMap(files)) {
  const lines = codeOnly(readFileSync(file, "utf8")).split("\n");
  lines.forEach((line, i) => {
    for (const [re, why] of BANNED) {
      if (re.test(line)) {
        console.error(`${relative(ROOT, file)}:${i + 1}: ${why}`);
        failures++;
      }
    }
  });
}
if (failures) {
  console.error(`determinism check failed: ${failures} problem(s)`);
  process.exit(1);
}
console.log("determinism check passed");
