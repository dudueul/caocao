using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Town : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_type;

	[SerializeField]
	private ObscuredInt m_grade;

	[SerializeField]
	private ObscuredInt m_towngrade;

	[SerializeField]
	private ObscuredInt m_defUni;

	[SerializeField]
	private ObscuredString m_prefab;

	[SerializeField]
	private ObscuredString m_forcePrefab;

	[SerializeField]
	private ObscuredInt m_isFlip;

	[SerializeField]
	private ObscuredInt m_state;

	[SerializeField]
	private ObscuredInt m_cloud;

	[SerializeField]
	private ObscuredInt m_position_X;

	[SerializeField]
	private ObscuredInt m_position_Y;

	[SerializeField]
	private ObscuredString m_eventScript;

	[SerializeField]
	private ObscuredInt m_tacticCounts;

	[SerializeField]
	private ObscuredInt m_battleMap;

	[SerializeField]
	private ObscuredInt m_expConst;

	[SerializeField]
	private ObscuredString m_bgTexture;

	[SerializeField]
	private ObscuredInt m_bgFlip;

	[SerializeField]
	private ObscuredInt m_envType;

	[SerializeField]
	private ObscuredInt m_castleFunctionGroups;

	[SerializeField]
	private ObscuredInt m_castleMaxLV;

	[SerializeField]
	private ObscuredInt m_foodPlantFunctionGroups;

	[SerializeField]
	private ObscuredInt m_foodPlantMaxLV;

	[SerializeField]
	private ObscuredInt m_silverPlantFunctionGroups;

	[SerializeField]
	private ObscuredInt m_silverPlantMaxLV;

	[SerializeField]
	private ObscuredInt m_foodStorageFunctionGroups;

	[SerializeField]
	private ObscuredInt m_foodStorageMaxLV;

	[SerializeField]
	private ObscuredInt m_silverStorageFunctionGroups;

	[SerializeField]
	private ObscuredInt m_silverStorageMaxLV;

	[SerializeField]
	private ObscuredInt m_safeFunctionGroups;

	[SerializeField]
	private ObscuredInt m_safeMaxLV;

	[SerializeField]
	private ObscuredInt m_type0;

	[SerializeField]
	private ObscuredInt m_reward0;

	[SerializeField]
	private ObscuredInt m_type1;

	[SerializeField]
	private ObscuredInt m_reward1;

	[SerializeField]
	private ObscuredInt m_popularityType;

	[SerializeField]
	private ObscuredInt m_popularityReward;

	public string name
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

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

	public int type
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int grade
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int towngrade
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int defUni
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string prefab
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string forcePrefab
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int isFlip
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int state
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int cloud
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int position_X
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int position_Y
	{
		get
		{
			return 0;
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

	public int tacticCounts
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int battleMap
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int expConst
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string bgTexture
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int bgFlip
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int envType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int castleFunctionGroups
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int castleMaxLV
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int foodPlantFunctionGroups
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int foodPlantMaxLV
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int silverPlantFunctionGroups
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int silverPlantMaxLV
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int foodStorageFunctionGroups
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int foodStorageMaxLV
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int silverStorageFunctionGroups
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int silverStorageMaxLV
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int safeFunctionGroups
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int safeMaxLV
	{
		get
		{
			return 0;
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

	public int reward1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int popularityType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int popularityReward
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}
}
