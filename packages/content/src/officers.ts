/**
 * Officers for the P0 sandbox. Names are historical (public domain); every
 * rating is our own, on the 1–100 scale of ADR 0020, set in steps of 5 from the
 * rubric in this package's README until the full officer pass. Nothing here
 * comes from Koei or Nexon data (ADR 0002).
 */
import type { Stats } from "@tk/rules";

export interface OfficerDef {
  readonly id: string;
  /** Display name (Korean reading). */
  readonly name: string;
  readonly hanja: string;
  readonly stats: Stats;
  /** A troop template, not a person: scenarios give each unit its own id (ADR 0049). */
  readonly generic?: boolean;
}

/** 무력, 통솔, 지력, 민첩, 운. */
type Ratings = readonly [number, number, number, number, number];

function officer(id: string, name: string, hanja: string, ratings: Ratings): OfficerDef {
  const [martial, command, intellect, agility, fortune] = ratings;
  return { id, name, hanja, stats: { martial, command, intellect, agility, fortune } };
}

function troop(id: string, name: string, hanja: string, ratings: Ratings): OfficerDef {
  return { ...officer(id, name, hanja, ratings), generic: true };
}

// biome-ignore format: one officer per line reads as a table
const LIST: readonly OfficerDef[] = [
  //                                         무력 통솔 지력 민첩 운
  officer("cao-cao",      "조조",   "曹操",   [70, 90, 90, 70, 85]),
  officer("xu-chu",       "허저",   "許褚",   [95, 60, 30, 70, 65]),
  officer("yue-jin",      "악진",   "樂進",   [80, 75, 50, 65, 60]),
  officer("xiahou-yuan",  "하후연", "夏侯淵", [80, 70, 50, 85, 55]),
  officer("xun-you",      "순유",   "荀攸",   [25, 55, 90, 50, 70]),
  officer("liu-ye",       "유엽",   "劉曄",   [35, 55, 80, 45, 60]),
  officer("chunyu-qiong", "순우경", "淳于瓊", [65, 65, 40, 50, 30]),
  officer("gao-lan",      "고람",   "高覽",   [75, 65, 40, 60, 55]),
  officer("zhang-he",     "장합",   "張郃",   [85, 80, 65, 75, 70]),
  officer("qu-yi",        "국의",   "麴義",   [75, 75, 45, 60, 40]),
  officer("ju-shou",      "저수",   "沮授",   [20, 70, 90, 45, 35]),
  officer("wen-chou",     "문추",   "文醜",   [90, 65, 25, 70, 45]),
  // Arena pool (ADR 0041)
  officer("lu-bu",        "여포",   "呂布",   [100, 75, 25, 90, 30]),
  officer("guan-yu",      "관우",   "關羽",   [95, 90, 65, 75, 55]),
  officer("zhao-yun",     "조운",   "趙雲",   [90, 80, 65, 85, 90]),
  officer("zhang-fei",    "장비",   "張飛",   [95, 75, 35, 70, 50]),
  officer("xiahou-dun",   "하후돈", "夏侯惇", [85, 85, 55, 65, 60]),
  officer("gan-ning",     "감녕",   "甘寧",   [90, 75, 50, 85, 55]),
  officer("huang-zhong",  "황충",   "黃忠",   [90, 80, 50, 70, 60]),
  officer("taishi-ci",    "태사자", "太史慈", [90, 75, 55, 80, 45]),
  officer("zhuge-liang",  "제갈량", "諸葛亮", [35, 90, 100, 60, 75]),
  officer("zhou-yu",      "주유",   "周瑜",   [70, 90, 95, 70, 40]),
  officer("guo-jia",      "곽가",   "郭嘉",   [20, 50, 95, 55, 30]),
  officer("dian-wei",     "전위",   "典韋",   [95, 65, 30, 65, 35]),
  officer("ma-jun",       "마균",   "馬鈞",   [20, 40, 85, 40, 55]),
  // Story chapter 1, Guandu (ADR 0049)
  officer("zhang-liao",   "장료",   "張遼",   [90, 90, 75, 80, 65]),
  officer("xu-huang",     "서황",   "徐晃",   [85, 80, 60, 65, 60]),
  officer("li-dian",      "이전",   "李典",   [70, 75, 70, 60, 60]),
  officer("yu-jin",       "우금",   "于禁",   [75, 85, 55, 55, 40]),
  officer("xun-yu",       "순욱",   "荀彧",   [15, 60, 95, 45, 60]),
  officer("xu-you",       "허유",   "許攸",   [20, 40, 80, 45, 35]),
  officer("liu-yan",      "유연",   "劉延",   [50, 65, 55, 45, 50]),
  officer("yuan-shao",    "원소",   "袁紹",   [65, 80, 70, 55, 60]),
  officer("yan-liang",    "안량",   "顏良",   [90, 70, 30, 70, 40]),
  officer("tian-feng",    "전풍",   "田豐",   [15, 60, 95, 40, 25]),
  officer("shen-pei",     "심배",   "審配",   [45, 75, 75, 45, 40]),
  officer("guo-tu",       "곽도",   "郭圖",   [30, 50, 75, 50, 45]),
  officer("feng-ji",      "봉기",   "逢紀",   [25, 50, 75, 45, 40]),
  officer("yuan-tan",     "원담",   "袁譚",   [65, 65, 55, 60, 55]),
  officer("han-meng",     "한맹",   "韓猛",   [75, 60, 35, 60, 45]),
  officer("jiang-qi",     "장기",   "蔣奇",   [70, 60, 35, 55, 40]),
  officer("liu-bei",      "유비",   "劉備",   [75, 80, 75, 65, 90]),
  // Story chapter 1, the Yellow Turbans to Yanzhou (ADR 0057)
  officer("cao-ren",      "조인",   "曹仁",   [80, 85, 55, 60, 65]),
  officer("cao-hong",     "조홍",   "曹洪",   [75, 70, 40, 65, 75]),
  officer("chen-gong",    "진궁",   "陳宮",   [40, 70, 85, 50, 40]),
  officer("bao-xin",      "포신",   "鮑信",   [70, 75, 65, 55, 30]),
  officer("wei-zi",       "위자",   "衛茲",   [40, 55, 60, 45, 30]),
  officer("huangfu-song", "황보숭", "皇甫嵩", [75, 90, 75, 60, 65]),
  officer("zhu-jun",      "주준",   "朱儁",   [75, 80, 60, 60, 55]),
  officer("sun-jian",     "손견",   "孫堅",   [90, 85, 65, 75, 40]),
  officer("xu-shao",      "허소",   "許劭",   [15, 30, 85, 40, 60]),
  officer("jian-tu",      "건도",   "蹇圖",   [40, 35, 35, 40, 25]),
  officer("zhang-jiao",   "장각",   "張角",   [30, 85, 90, 45, 60]),
  officer("bo-cai",       "파재",   "波才",   [70, 70, 55, 60, 45]),
  officer("dong-zhuo",    "동탁",   "董卓",   [80, 85, 70, 55, 70]),
  officer("hua-xiong",    "화웅",   "華雄",   [90, 70, 30, 70, 40]),
  officer("xu-rong",      "서영",   "徐榮",   [80, 85, 65, 65, 55]),
  officer("bai-rao",      "백요",   "白繞",   [70, 60, 35, 60, 40]),
  // Story chapter 2, 여포 (ADR 0059)
  officer("cheng-yu",     "정욱",   "程昱",   [45, 70, 85, 50, 55]),
  officer("cao-ang",      "조앙",   "曹昂",   [60, 60, 55, 60, 30]),
  officer("cao-song",     "조숭",   "曹嵩",   [20, 30, 50, 30, 20]),
  officer("tao-qian",     "도겸",   "陶謙",   [40, 65, 60, 40, 50]),
  officer("cao-bao",      "조표",   "曹豹",   [65, 60, 35, 55, 35]),
  officer("zhang-miao",   "장막",   "張邈",   [50, 65, 60, 45, 40]),
  officer("gao-shun",     "고순",   "高順",   [85, 85, 50, 60, 35]),
  officer("hou-cheng",    "후성",   "侯成",   [65, 55, 35, 55, 40]),
  officer("song-xian",    "송헌",   "宋憲",   [65, 55, 30, 55, 40]),
  officer("wei-xu",       "위속",   "魏續",   [65, 55, 30, 55, 35]),
  officer("zang-ba",      "장패",   "臧霸",   [80, 75, 50, 65, 55]),
  officer("chen-deng",    "진등",   "陳登",   [55, 70, 85, 55, 60]),
  officer("zhang-xiu",    "장수",   "張繡",   [80, 75, 55, 70, 55]),
  officer("jia-xu",       "가후",   "賈詡",   [35, 70, 95, 55, 75]),
  officer("hu-cheer",     "호거아", "胡車兒", [80, 50, 25, 75, 40]),
  officer("yuan-shu",     "원술",   "袁術",   [60, 70, 60, 50, 50]),
  officer("qiao-rui",     "교유",   "橋蕤",   [70, 65, 40, 55, 35]),
  officer("zhang-xun",    "장훈",   "張勳",   [70, 70, 45, 55, 40]),
  officer("ji-ling",      "기령",   "紀靈",   [85, 70, 40, 60, 40]),
  officer("xian-di",      "헌제",   "獻帝",   [10, 20, 55, 30, 50]),
  officer("yang-feng",    "양봉",   "楊奉",   [60, 55, 35, 55, 35]),
  officer("man-chong",    "만총",   "滿寵",   [55, 70, 80, 50, 60]),
  officer("liu-biao",     "유표",   "劉表",   [40, 70, 75, 40, 60]),
  officer("tian-shi",     "전씨",   "田氏",   [30, 35, 50, 40, 30]),
  // Troop templates: each unit is named after its army and class.
  troop("yuan",           "원소군", "袁軍",   [60, 55, 45, 50, 40]),
  troop("yuan-elite",     "원소군 정예", "袁軍", [70, 65, 50, 60, 45]),
  troop("cao",            "조조군", "曹軍",   [60, 60, 45, 55, 45]),
  troop("ward",           "북부위", "北部尉", [55, 50, 35, 50, 45]),
  troop("jianjia",        "건가 가노", "蹇家", [50, 40, 25, 50, 30]),
  troop("huangjin",       "황건적", "黃巾",   [55, 45, 40, 50, 40]),
  troop("qingzhou",       "청주 황건", "青州", [60, 50, 40, 55, 45]),
  troop("dong",           "동탁군", "董軍",   [65, 60, 40, 55, 40]),
  troop("heishan",        "흑산적", "黑山",   [60, 50, 35, 60, 40]),
  troop("xu",             "서주군", "徐州",   [55, 55, 40, 50, 40]),
  troop("lu",             "여포군", "呂軍",   [65, 60, 35, 60, 40]),
  troop("xianzhen",       "함진영", "陷陣",   [75, 70, 40, 60, 40]),
  troop("zhang",          "장수군", "張軍",   [60, 60, 40, 55, 40]),
  troop("jing",           "형주군", "荊州",   [55, 55, 45, 50, 40]),
  troop("zhong",          "원술군", "仲軍",   [55, 55, 40, 50, 35]),
];

export const OFFICERS: Readonly<Record<string, OfficerDef>> = Object.fromEntries(
  LIST.map((o) => [o.id, o]),
);
