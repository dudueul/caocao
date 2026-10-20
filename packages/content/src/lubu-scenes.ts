/**
 * Chapter 2 (여포) scenes and camps, staged on tile maps (ADR 0063). The two
 * choices live here: 서주 (`xuzhou`: revenge / mercy) and 백문루 (`lubu`:
 * executed / spared); 전위's fate (`dianwei`: lived / fell) is decided at
 * 완성 (ADR 0060, 0061). Our own words throughout (ADR 0002).
 *
 * PLACEHOLDER STAGING: each scene keeps its id, roster changes and choices;
 * the script is being written out in full.
 */
import { CAMP_GROUND, staged, tell } from "./stage.ts";
import type { CampDef, Cond, SceneDef } from "./story.ts";

const revenge: Cond = { flag: "xuzhou", is: "revenge" };
const mercy: Cond = { flag: "xuzhou", is: "mercy" };
const fell: Cond = { flag: "dianwei", is: "fell" };
const executed: Cond = { flag: "lubu", is: "executed" };
const spared: Cond = { flag: "lubu", is: "spared" };

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
  "lubu-prologue",
  "아버지의 피",
  "연주 견성",
  "초평 4년(193)",
  "court",
  "조숭이 서주 땅에서 죽었다.",
);
export const BEFORE_PENGCHENG = scene(
  "lubu-before-pengcheng",
  "복수의 깃발",
  "서주 팽성",
  "초평 4년(193) 가을",
  "camp",
  "조조의 군대가 서주로 향했다.",
);

export const XUZHOU: SceneDef = {
  ...scene("lubu-xuzhou", "사수의 강물", "서주", "초평 4년(193) 겨울", "fire", "팽성이 무너졌다."),
  script: [
    tell("팽성이 무너졌다. 서주의 고을들이 눈앞에 있었다."),
    {
      do: "choice",
      flag: "xuzhou",
      prompt: "아버지의 원수를 어떻게 갚을 것인가?",
      options: [
        {
          value: "revenge",
          text: "서주의 고을을 모조리 쓸어버려라",
          gold: 1500,
          items: ["fine-horse"],
        },
        { value: "mercy", text: "칼을 거두어라. 원수는 도겸 하나다" },
      ],
    },
    { ...tell("고을마다 불길이 올랐다."), if: revenge },
    { ...tell("조조는 칼을 거두었다."), if: mercy },
  ],
};

export const REVOLT = scene(
  "lubu-revolt",
  "등 뒤의 배반",
  "연주",
  "흥평 원년(194)",
  "night",
  "연주가 등을 돌렸다.",
  {
    leaves: [{ officer: "chen-gong", if: revenge }],
    joins: [
      { officer: "cheng-yu", classId: "strategist", level: 11, gear: ["feather-fan", "robe"] },
    ],
  },
);
export const DIAN_WEI = scene(
  "lubu-dian-wei",
  "아문기를 든 사내",
  "연주 진영",
  "흥평 원년(194)",
  "camp",
  "전위가 왔다.",
  {
    joins: [
      {
        officer: "dian-wei",
        classId: "martialArtist",
        level: 12,
        gear: ["refined-dao", "leather"],
      },
    ],
  },
);
export const BEFORE_PUYANG = scene(
  "lubu-before-puyang",
  "전씨의 밀서",
  "복양 성밖",
  "흥평 원년(194) 여름",
  "night",
  "전씨의 밀서가 왔다.",
);
export const AFTER_PUYANG = scene(
  "lubu-after-puyang",
  "타다 남은 수염",
  "복양 진영",
  "흥평 원년(194)",
  "camp",
  "조조는 불길을 빠져나왔다.",
);
export const BEFORE_DINGTAO = scene(
  "lubu-before-dingtao",
  "둑 뒤의 숲",
  "정도",
  "흥평 2년(195) 여름",
  "field",
  "여포가 다시 왔다.",
);
export const AFTER_DINGTAO = scene(
  "lubu-after-dingtao",
  "연주를 되찾다",
  "연주",
  "흥평 2년(195)",
  "dawn",
  "여포는 서주로 달아났다.",
);
export const EMPEROR = scene(
  "lubu-emperor",
  "천자를 모시다",
  "낙양 → 허",
  "건안 원년(196)",
  "court",
  "천자가 허로 왔다.",
  {
    joins: [
      { officer: "xu-huang", classId: "infantry", level: 13, gear: ["ring-dao", "lamellar"] },
      { officer: "xun-you", classId: "strategist", level: 12, gear: ["feather-fan", "robe"] },
      { officer: "guo-jia", classId: "strategist", level: 12, gear: ["feather-fan", "robe"] },
    ],
  },
);
export const FEAST = scene(
  "lubu-wancheng",
  "완성의 술자리",
  "완성",
  "건안 2년(197) 봄",
  "night",
  "장수가 항복했다.",
);
export const AFTER_WANCHENG = scene(
  "lubu-after-wancheng",
  "육수의 새벽",
  "육수",
  "건안 2년(197)",
  "dawn",
  "날이 밝았다.",
  {
    leaves: [{ officer: "dian-wei", if: fell }],
    joins: [
      { officer: "xu-chu", classId: "martialArtist", level: 14, gear: ["ring-dao", "leather"] },
    ],
  },
);
export const BEFORE_QI = scene(
  "lubu-before-qi",
  "가짜 황제",
  "진국",
  "건안 2년(197) 가을",
  "camp",
  "원술이 황제를 칭했다.",
);
export const BEFORE_ANZHONG = scene(
  "lubu-before-anzhong",
  "퇴로의 함정",
  "양성",
  "건안 3년(198) 봄",
  "field",
  "유표가 길을 막았다.",
);
export const AFTER_ANZHONG = scene(
  "lubu-after-anzhong",
  "서주에서 온 편지",
  "허",
  "건안 3년(198) 가을",
  "court",
  "유비가 허로 왔다.",
);
export const BEFORE_XIAPI = scene(
  "lubu-before-xiapi",
  "얼어붙은 성",
  "하비",
  "건안 3년(198) 겨울",
  "river",
  "하비는 굳게 닫혔다.",
);

export const WHITE_GATE: SceneDef = {
  ...scene(
    "lubu-white-gate",
    "백문루",
    "하비 백문루",
    "건안 3년(198) 12월",
    "court",
    "여포가 묶여 왔다.",
  ),
  script: [
    tell("여포가 묶여 왔다."),
    {
      do: "choice",
      flag: "lubu",
      prompt: "여포를 어떻게 할 것인가?",
      options: [
        { value: "executed", text: "목을 매달아라" },
        { value: "spared", text: "포승을 풀어 주어라" },
      ],
    },
  ],
  leaves: [{ officer: "zhang-liao", if: spared }],
  joins: [
    {
      officer: "zhang-liao",
      classId: "cavalry",
      level: 16,
      gear: ["iron-ji", "lamellar"],
      if: executed,
    },
    {
      officer: "lu-bu",
      classId: "shockCavalry",
      level: 18,
      gear: ["sky-ji", "lamellar", "red-hare"],
      if: spared,
    },
  ],
};
export const EPILOGUE = scene(
  "lubu-epilogue",
  "북쪽의 구름",
  "허",
  "건안 4년(199)",
  "court",
  "북쪽에서 원소가 일어섰다.",
);

const SHOP_1 = [
  "refined-dao",
  "horse-lance",
  "strong-bow",
  "feather-fan",
  "scale",
  "lacquer",
  "padded-robe",
  "jade",
];
export const CAMP_1: CampDef = { id: "lubu-camp-1", shop: SHOP_1, ground: CAMP_GROUND };
export const CAMP_2: CampDef = {
  id: "lubu-camp-2",
  shop: [...SHOP_1, "fine-horse"],
  ground: CAMP_GROUND,
};
export const CAMP_3: CampDef = {
  id: "lubu-camp-3",
  shop: [...SHOP_1, "crane-fan", "iron-shot", "seal-cord"],
  ground: CAMP_GROUND,
};
export const CAMP_4: CampDef = {
  id: "lubu-camp-4",
  shop: [...SHOP_1, "crane-fan", "iron-shot", "hundred-dao", "great-ji", "iron-bow", "seal-cord"],
  ground: CAMP_GROUND,
};
