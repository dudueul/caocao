/**
 * Equipment for the Story roster (ADR 0050): one weapon, one armour and one
 * accessory per officer. Weapons add ATK and armour adds DEF (the `atkBonus` and
 * `defBonus` the rules already carry); accessories and a few weapons add stats.
 * Names are period objects of the late Eastern Han; every number is our own.
 */
import type { FamilyId, Stats } from "@tk/rules";

export type Slot = "weapon" | "armour" | "accessory";
export type WeaponKind = "blade" | "polearm" | "bow" | "fan" | "shot";
export type ArmourKind = "heavy" | "light" | "robe";

export interface ItemDef {
  readonly id: string;
  readonly name: string;
  readonly hanja: string;
  readonly slot: Slot;
  /** Which officers can use it: a weapon or armour kind; accessories fit anyone. */
  readonly kind?: WeaponKind | ArmourKind;
  readonly atk?: number;
  readonly def?: number;
  readonly stats?: Partial<Stats>;
  /** Shop price in gold; selling returns half. Rewards-only items still have a price. */
  readonly price: number;
  /** One line for the shop and the roster. */
  readonly text: string;
}

/** What each family can wield and wear. */
export const GEAR: Readonly<
  Record<FamilyId, { weapon: readonly WeaponKind[]; armour: readonly ArmourKind[] }>
> = {
  infantry: { weapon: ["blade", "polearm"], armour: ["heavy", "light"] },
  spearman: { weapon: ["polearm"], armour: ["heavy", "light"] },
  archer: { weapon: ["bow"], armour: ["light"] },
  crossbow: { weapon: ["bow"], armour: ["light", "heavy"] },
  cavalry: { weapon: ["polearm", "blade"], armour: ["heavy", "light"] },
  horseArcher: { weapon: ["bow"], armour: ["light"] },
  catapult: { weapon: ["shot"], armour: ["light", "heavy"] },
  martialArtist: { weapon: ["blade"], armour: ["light"] },
  bandit: { weapon: ["blade"], armour: ["light"] },
  strategist: { weapon: ["fan"], armour: ["robe"] },
  taoist: { weapon: ["fan"], armour: ["robe"] },
  dancer: { weapon: ["blade"], armour: ["robe"] },
};

function item(def: ItemDef): ItemDef {
  return def;
}

const LIST: readonly ItemDef[] = [
  // Weapons
  item({
    id: "ring-dao",
    name: "환수도",
    hanja: "環首刀",
    slot: "weapon",
    kind: "blade",
    atk: 8,
    price: 300,
    text: "고리 자루의 외날 칼. 한나라 병사의 기본 무기.",
  }),
  item({
    id: "refined-dao",
    name: "백련도",
    hanja: "百鍊刀",
    slot: "weapon",
    kind: "blade",
    atk: 16,
    price: 900,
    text: "백 번 접어 두드린 강철 칼.",
  }),
  item({
    id: "hundred-dao",
    name: "백벽도",
    hanja: "百辟刀",
    slot: "weapon",
    kind: "blade",
    atk: 26,
    stats: { martial: 3 },
    price: 2400,
    text: "조조가 장인에게 벼리게 한 다섯 자루 중 하나.",
  }),
  item({
    id: "iron-ji",
    name: "철극",
    hanja: "鐵戟",
    slot: "weapon",
    kind: "polearm",
    atk: 8,
    price: 300,
    text: "찌르고 걸어 당기는 쇠 극.",
  }),
  item({
    id: "horse-lance",
    name: "마삭",
    hanja: "馬槊",
    slot: "weapon",
    kind: "polearm",
    atk: 16,
    price: 900,
    text: "말 위에서 쓰는 긴 창.",
  }),
  item({
    id: "great-ji",
    name: "대극",
    hanja: "大戟",
    slot: "weapon",
    kind: "polearm",
    atk: 26,
    stats: { agility: 3 },
    price: 2400,
    text: "명장의 손에 맞춘 무거운 극.",
  }),
  item({
    id: "sky-ji",
    name: "방천화극",
    hanja: "方天畫戟",
    slot: "weapon",
    kind: "polearm",
    atk: 32,
    stats: { martial: 4 },
    price: 3200,
    text: "초승달 날을 양쪽에 단 화극. 여포의 손에서 천하를 떨게 했다.",
  }),
  item({
    id: "horn-bow",
    name: "각궁",
    hanja: "角弓",
    slot: "weapon",
    kind: "bow",
    atk: 8,
    price: 300,
    text: "뿔과 힘줄을 겹쳐 만든 활.",
  }),
  item({
    id: "strong-bow",
    name: "강궁",
    hanja: "強弓",
    slot: "weapon",
    kind: "bow",
    atk: 16,
    price: 900,
    text: "당기기 힘든 만큼 멀리 나간다.",
  }),
  item({
    id: "iron-bow",
    name: "철태궁",
    hanja: "鐵胎弓",
    slot: "weapon",
    kind: "bow",
    atk: 26,
    stats: { agility: 3 },
    price: 2400,
    text: "쇠를 심에 넣은 명궁.",
  }),
  item({
    id: "feather-fan",
    name: "우선",
    hanja: "羽扇",
    slot: "weapon",
    kind: "fan",
    atk: 4,
    stats: { intellect: 4 },
    price: 300,
    text: "깃털 부채. 군사를 지휘할 때 든다.",
  }),
  item({
    id: "crane-fan",
    name: "백우선",
    hanja: "白羽扇",
    slot: "weapon",
    kind: "fan",
    atk: 8,
    stats: { intellect: 8 },
    price: 1000,
    text: "흰 깃의 부채. 손짓 하나에 진이 바뀐다.",
  }),
  item({
    id: "stone-shot",
    name: "석환",
    hanja: "石丸",
    slot: "weapon",
    kind: "shot",
    atk: 8,
    price: 300,
    text: "둥글게 다듬은 돌 탄.",
  }),
  item({
    id: "iron-shot",
    name: "철환",
    hanja: "鐵丸",
    slot: "weapon",
    kind: "shot",
    atk: 16,
    price: 900,
    text: "무쇠를 부어 만든 탄. 망루도 무너뜨린다.",
  }),
  // Armour
  item({
    id: "lamellar",
    name: "찰갑",
    hanja: "札甲",
    slot: "armour",
    kind: "heavy",
    def: 8,
    price: 300,
    text: "쇠 미늘을 끈으로 엮은 갑옷.",
  }),
  item({
    id: "scale",
    name: "어린갑",
    hanja: "魚鱗甲",
    slot: "armour",
    kind: "heavy",
    def: 16,
    price: 900,
    text: "물고기 비늘처럼 겹친 미늘 갑옷.",
  }),
  item({
    id: "bright-armour",
    name: "명광개",
    hanja: "明光鎧",
    slot: "armour",
    kind: "heavy",
    def: 26,
    stats: { command: 3 },
    price: 2400,
    text: "가슴의 둥근 판이 햇빛에 번쩍인다.",
  }),
  item({
    id: "leather",
    name: "피갑",
    hanja: "皮甲",
    slot: "armour",
    kind: "light",
    def: 6,
    price: 250,
    text: "옻칠한 가죽 갑옷. 가볍다.",
  }),
  item({
    id: "lacquer",
    name: "칠피갑",
    hanja: "漆皮甲",
    slot: "armour",
    kind: "light",
    def: 12,
    stats: { agility: 2 },
    price: 800,
    text: "여러 겹 옻칠해 단단하게 굳힌 가죽.",
  }),
  item({
    id: "robe",
    name: "심의",
    hanja: "深衣",
    slot: "armour",
    kind: "robe",
    def: 4,
    stats: { intellect: 2 },
    price: 200,
    text: "선비의 긴 옷.",
  }),
  item({
    id: "padded-robe",
    name: "면포",
    hanja: "綿袍",
    slot: "armour",
    kind: "robe",
    def: 9,
    stats: { fortune: 3 },
    price: 700,
    text: "솜을 둔 두꺼운 도포. 화살을 잡아 준다.",
  }),
  // Accessories
  item({
    id: "sunzi",
    name: "손자병법",
    hanja: "孫子兵法",
    slot: "accessory",
    stats: { intellect: 8 },
    price: 1600,
    text: "조조가 직접 주석을 단 병법서.",
  }),
  item({
    id: "tiger-tally",
    name: "호부",
    hanja: "虎符",
    slot: "accessory",
    stats: { command: 5, martial: 5 },
    price: 1600,
    text: "군대를 움직이는 범 모양 부절.",
  }),
  item({
    id: "fine-horse",
    name: "준마",
    hanja: "駿馬",
    slot: "accessory",
    stats: { agility: 8 },
    price: 1200,
    text: "하루에 천 리를 달리는 말.",
  }),
  item({
    id: "red-hare",
    name: "적토마",
    hanja: "赤兎馬",
    slot: "accessory",
    stats: { agility: 12, martial: 4 },
    price: 3200,
    text: "온몸이 숯불처럼 붉은 명마. 사람 가운데 여포, 말 가운데 적토.",
  }),
  item({
    id: "jade",
    name: "옥패",
    hanja: "玉佩",
    slot: "accessory",
    stats: { fortune: 8 },
    price: 800,
    text: "몸에 지니면 화를 피한다는 옥.",
  }),
  item({
    id: "seal-cord",
    name: "인수",
    hanja: "印綬",
    slot: "accessory",
    stats: { command: 4, intellect: 4 },
    price: 1200,
    text: "관인과 끈. 직책의 무게를 느낀다.",
  }),
];

export const ITEMS: Readonly<Record<string, ItemDef>> = Object.fromEntries(
  LIST.map((i) => [i.id, i]),
);
