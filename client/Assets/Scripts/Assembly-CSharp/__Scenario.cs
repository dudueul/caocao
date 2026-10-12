using System;
using System.Collections.Generic;

[Serializable]
public class __Scenario
{
	public int id;

	public int StorybookId;

	public int scenarioKind;

	public int BattlemapId;

	public string accidentName;

	public string placeName;

	public int year;

	public string eraName;

	public int eraYear;

	public string atlas;

	public string chartImage;

	public string battleImage;

	public string cutInImage;

	public int chartRow;

	public int chartColumn;

	public int chapterNo;

	public string episodeNo;

	public List<int> nextScenario;

	public int difficulty;

	public int bossName;

	public int chartType;

	public int needGold;

	public int needFood;

	public int needAttackFood;

	public string needAmbition;

	public List<int> checkSaveFlag;

	public List<int> needUnit;

	public string eventScript;

	public int shopLv;

	public int shopId;

	public string mapDescription;

	public int missionsId01;

	public int missionsId02;

	public int missionsId03;

	public int missionsId01Hard;

	public int missionsId02Hard;

	public int missionsId03Hard;

	public int missionsId01Classic;

	public int missionsId02Classic;

	public int missionsId03Classic;

	public List<int> banSkill;

	public int type0;

	public int reward0;

	public int type1;

	public int rewardM1;

	public int type2;

	public int rewardM2;

	public int type3;

	public int rewardM3;

	public int typeH;

	public int rewardH;

	public int typeH1;

	public int rewardM1H;

	public int typeH2;

	public int rewardM2H;

	public int typeH3;

	public int rewardM3H;

	public int typeC;

	public int rewardC;

	public int typeC1;

	public int rewardM1C;

	public int typeC2;

	public int rewardM2C;

	public int typeC3;

	public int rewardM3C;

	public int rank;

	public int time;

	public string openDate;

	public void CopyTo(Scenario dest)
	{
	}

	public void Reset()
	{
	}
}
