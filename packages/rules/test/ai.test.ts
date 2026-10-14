import { describe, expect, it } from "bun:test";
import { chooseCommand, createBattle, legalCommands } from "../src/index.ts";
import { RULES, setup, unit } from "./fixtures.ts";

describe("baseline AI (ADR 0027)", () => {
  it("never spends MP on a cast that does nothing, such as healing a full-HP ally", () => {
    const s = createBattle(
      RULES,
      setup(
        ["........"],
        [
          unit("s", 0, "strategist", 0, 0),
          unit("ally", 0, "infantry", 1, 0),
          unit("e", 1, "infantry", 7, 0),
        ],
      ),
    );
    const heals = legalCommands(s, RULES).filter(
      (c) => c.kind === "act" && c.action.kind === "stratagem" && c.action.id === "heal",
    );
    expect(heals.length).toBeGreaterThan(0); // legal, just pointless
    const c = chooseCommand(s, RULES);
    expect(c?.kind === "act" && c.action.kind === "stratagem").toBe(false);
  });

  it("heals a wounded ally", () => {
    const s = createBattle(
      RULES,
      setup(
        ["........"],
        [
          unit("s", 1, "strategist", 0, 0),
          unit("ally", 1, "infantry", 1, 0),
          unit("e", 0, "infantry", 7, 0),
        ],
      ),
    );
    s.active = 1;
    s.units = s.units.map((u) => (u.id === "ally" ? { ...u, hp: 40 } : u));
    const c = chooseCommand(s, RULES);
    expect(c?.kind === "act" && c.action.kind === "stratagem" && c.action.id).toBe("heal");
  });

  it("walks round a river to the crossing instead of waiting on the near bank", () => {
    // The only crossing is the bridge in the bottom row.
    const rows = ["...w...", "...w...", "...w...", "...b..."];
    const s = createBattle(
      RULES,
      setup(rows, [unit("me", 0, "infantry", 2, 0), unit("e", 1, "infantry", 4, 0)]),
    );
    const c = chooseCommand(s, RULES);
    if (c?.kind !== "act") throw new Error("expected an action");
    expect(c.to.y).toBeGreaterThan(0);
  });
});
