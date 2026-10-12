using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Scenario : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_StorybookId;

	[SerializeField]
	private ObscuredInt m_scenarioKind;

	[SerializeField]
	private ObscuredInt m_BattlemapId;

	[SerializeField]
	private ObscuredString m_accidentName;

	[SerializeField]
	private ObscuredString m_placeName;

	[SerializeField]
	private ObscuredInt m_year;

	[SerializeField]
	private ObscuredString m_eraName;

	[SerializeField]
	private ObscuredInt m_eraYear;

	[SerializeField]
	private ObscuredString m_atlas;

	[SerializeField]
	private ObscuredString m_chartImage;

	[SerializeField]
	private ObscuredString m_battleImage;

	[SerializeField]
	private ObscuredString m_cutInImage;

	[SerializeField]
	private ObscuredInt m_chartRow;

	[SerializeField]
	private ObscuredInt m_chartColumn;

	[SerializeField]
	private ObscuredInt m_chapterNo;

	[SerializeField]
	private ObscuredString m_episodeNo;

	[SerializeField]
	private List<ObscuredInt> m_nextScenario;

	[SerializeField]
	private ObscuredInt m_difficulty;

	[SerializeField]
	private ObscuredInt m_bossName;

	[SerializeField]
	private ObscuredInt m_chartType;

	[SerializeField]
	private ObscuredInt m_needGold;

	[SerializeField]
	private ObscuredInt m_needFood;

	[SerializeField]
	private ObscuredInt m_needAttackFood;

	[SerializeField]
	private ObscuredString m_needAmbition;

	[SerializeField]
	private List<ObscuredInt> m_checkSaveFlag;

	[SerializeField]
	private List<ObscuredInt> m_needUnit;

	[SerializeField]
	private ObscuredString m_eventScript;

	[SerializeField]
	private ObscuredInt m_shopLv;

	[SerializeField]
	private ObscuredInt m_shopId;

	[SerializeField]
	private ObscuredString m_mapDescription;

	[SerializeField]
	private ObscuredInt m_missionsId01;

	[SerializeField]
	private ObscuredInt m_missionsId02;

	[SerializeField]
	private ObscuredInt m_missionsId03;

	[SerializeField]
	private ObscuredInt m_missionsId01Hard;

	[SerializeField]
	private ObscuredInt m_missionsId02Hard;

	[SerializeField]
	private ObscuredInt m_missionsId03Hard;

	[SerializeField]
	private ObscuredInt m_missionsId01Classic;

	[SerializeField]
	private ObscuredInt m_missionsId02Classic;

	[SerializeField]
	private ObscuredInt m_missionsId03Classic;

	[SerializeField]
	private List<ObscuredInt> m_banSkill;

	[SerializeField]
	private ObscuredInt m_type0;

	[SerializeField]
	private ObscuredInt m_reward0;

	[SerializeField]
	private ObscuredInt m_type1;

	[SerializeField]
	private ObscuredInt m_rewardM1;

	[SerializeField]
	private ObscuredInt m_type2;

	[SerializeField]
	private ObscuredInt m_rewardM2;

	[SerializeField]
	private ObscuredInt m_type3;

	[SerializeField]
	private ObscuredInt m_rewardM3;

	[SerializeField]
	private ObscuredInt m_typeH;

	[SerializeField]
	private ObscuredInt m_rewardH;

	[SerializeField]
	private ObscuredInt m_typeH1;

	[SerializeField]
	private ObscuredInt m_rewardM1H;

	[SerializeField]
	private ObscuredInt m_typeH2;

	[SerializeField]
	private ObscuredInt m_rewardM2H;

	[SerializeField]
	private ObscuredInt m_typeH3;

	[SerializeField]
	private ObscuredInt m_rewardM3H;

	[SerializeField]
	private ObscuredInt m_typeC;

	[SerializeField]
	private ObscuredInt m_rewardC;

	[SerializeField]
	private ObscuredInt m_typeC1;

	[SerializeField]
	private ObscuredInt m_rewardM1C;

	[SerializeField]
	private ObscuredInt m_typeC2;

	[SerializeField]
	private ObscuredInt m_rewardM2C;

	[SerializeField]
	private ObscuredInt m_typeC3;

	[SerializeField]
	private ObscuredInt m_rewardM3C;

	[SerializeField]
	private ObscuredInt m_rank;

	[SerializeField]
	private ObscuredInt m_time;

	[SerializeField]
	private ObscuredString m_openDate;

	public int id
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int StorybookId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int scenarioKind
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int BattlemapId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string accidentName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string placeName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int year
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string eraName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int eraYear
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string atlas
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string chartImage
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string battleImage
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string cutInImage
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int chartRow
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int chartColumn
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int chapterNo
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string episodeNo
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> nextScenario
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int difficulty
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int bossName
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int chartType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int needGold
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int needFood
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int needAttackFood
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string needAmbition
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> checkSaveFlag
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> needUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string eventScript
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int shopLv
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int shopId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string mapDescription
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int missionsId01
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int missionsId02
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int missionsId03
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int missionsId01Hard
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int missionsId02Hard
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int missionsId03Hard
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int missionsId01Classic
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int missionsId02Classic
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int missionsId03Classic
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> banSkill
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int type0
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int reward0
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int type1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rewardM1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int type2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rewardM2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int type3
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rewardM3
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int typeH
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rewardH
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int typeH1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rewardM1H
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int typeH2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rewardM2H
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int typeH3
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rewardM3H
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int typeC
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rewardC
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int typeC1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rewardM1C
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int typeC2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rewardM2C
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int typeC3
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rewardM3C
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rank
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int time
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string openDate
	{
		get
		{
			return null;
		}
		set
		{
		}
	}
}
