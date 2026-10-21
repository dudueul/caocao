/**
 * Chapter 4 (하북 평정) scenes and camps, staged on tile maps (ADR 0063). The
 * choice lives here: before the march to 백랑산, whether 곽가 comes along
 * (`guojia`: went / stayed, ADR 0068). Our own words throughout (ADR 0002).
 *
 * PLACEHOLDER STAGING: each scene keeps its id, roster changes and choice;
 * the script is being written out in full.
 */
import { CAMP_GROUND, staged, tell } from "./stage.ts";
import type { CampDef, Cond, SceneDef } from "./story.ts";

const went: Cond = { flag: "guojia", is: "went" };

function scene(
  id: string,
  title: string,
  place: string,
  when: string,
  backdrop: SceneDef["backdrop"],
  text: string,
  extra: Partial<SceneDef> = {},
): SceneDef {
  return { id, title, place, when, backdrop, ...staged(backdrop, [{ text }]), ...extra };
}

export const PROLOGUE = scene(
  "hebei-prologue",
  "남은 불씨",
  "창정",
  "건안 6년(201) 봄",
  "field",
  "원소가 다시 군사를 모았다.",
);
export const BEFORE_RUNAN = scene(
  "hebei-before-runan",
  "남쪽의 그림자",
  "허",
  "건안 6년(201)",
  "court",
  "유비가 여남에서 일어났다.",
);
export const AFTER_RUNAN = scene(
  "hebei-after-runan",
  "형주로 간 사내",
  "여남",
  "건안 7년(202)",
  "camp",
  "유비는 유표에게 달아났다.",
);
export const BEFORE_LIYANG = scene(
  "hebei-before-liyang",
  "원씨의 형제",
  "여양",
  "건안 8년(203) 봄",
  "field",
  "원담과 원상이 여양을 지켰다.",
);
export const AFTER_LIYANG = scene(
  "hebei-after-liyang",
  "형제의 칼",
  "여양",
  "건안 8년(203)",
  "camp",
  "원담이 항복을 청했다.",
  {
    joins: [{ officer: "xin-pi", classId: "strategist", level: 19, gear: ["crane-fan", "robe"] }],
  },
);
export const BEFORE_YE = scene(
  "hebei-before-ye",
  "장하의 물길",
  "업",
  "건안 9년(204) 봄",
  "river",
  "업을 에워쌌다.",
);
export const AFTER_YE = scene(
  "hebei-after-ye",
  "업의 주인",
  "업",
  "건안 9년(204) 8월",
  "court",
  "업이 함락되었다.",
  {
    leaves: ["xu-you"],
  },
);
export const BEFORE_NANPI = scene(
  "hebei-before-nanpi",
  "얼어붙은 강",
  "남피",
  "건안 10년(205) 정월",
  "night",
  "원담이 등을 돌렸다.",
  {
    joins: [{ officer: "cao-chun", classId: "cavalry", level: 20, gear: ["horse-lance", "scale"] }],
  },
);
export const AFTER_NANPI = scene(
  "hebei-after-nanpi",
  "북쪽으로 간 형제",
  "남피",
  "건안 10년(205)",
  "dawn",
  "원담이 죽었다.",
);
export const BEFORE_HUGUAN = scene(
  "hebei-before-huguan",
  "북상 태항산",
  "태항산",
  "건안 11년(206) 정월",
  "night",
  "고간이 호관에 들어갔다.",
);

export const BEFORE_BAILANG: SceneDef = {
  ...scene(
    "hebei-before-bailang",
    "노룡새의 옛길",
    "무종",
    "건안 12년(207) 여름",
    "camp",
    "오환을 치러 간다.",
  ),
  script: [
    tell("오환을 치러 북쪽으로 간다."),
    {
      do: "choice",
      flag: "guojia",
      prompt: "병든 곽가를 어떻게 할 것인가?",
      options: [
        { value: "went", text: "봉효의 계책이 필요하다. 함께 간다" },
        { value: "stayed", text: "봉효는 허로 돌아가 몸을 돌보게 하라" },
      ],
    },
  ],
  joins: [{ officer: "tian-chou", classId: "strategist", level: 19, gear: ["crane-fan", "robe"] }],
};
export const AFTER_BAILANG = scene(
  "hebei-after-bailang",
  "백랑산의 바람",
  "백랑산",
  "건안 12년(207) 8월",
  "field",
  "답돈이 쓰러졌다.",
);
export const EPILOGUE: SceneDef = {
  ...scene(
    "hebei-epilogue",
    "갈석에 올라",
    "역주 · 갈석산",
    "건안 12년(207) 가을",
    "dawn",
    "조조는 바다를 보았다.",
  ),
  leaves: [{ officer: "guo-jia", if: went }],
};

const SHOP_1 = [
  "hundred-dao",
  "great-ji",
  "iron-bow",
  "crane-fan",
  "bright-armour",
  "scale",
  "lacquer",
  "padded-robe",
  "fine-horse",
  "seal-cord",
];
export const CAMP_1: CampDef = { id: "hebei-camp-1", shop: SHOP_1, ground: CAMP_GROUND };
export const CAMP_2: CampDef = {
  id: "hebei-camp-2",
  shop: [...SHOP_1, "sunzi"],
  ground: CAMP_GROUND,
};
export const CAMP_3: CampDef = {
  id: "hebei-camp-3",
  shop: [...SHOP_1, "sunzi", "tiger-tally"],
  ground: CAMP_GROUND,
};
export const CAMP_4: CampDef = {
  id: "hebei-camp-4",
  shop: [...SHOP_1, "sunzi", "tiger-tally", "jade"],
  ground: CAMP_GROUND,
};
