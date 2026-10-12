using System.Collections;
using System.Diagnostics;
using GuildWar;
using UnityEngine;

public class GuildWarRendererBuild : GuildWarRendererObject
{
	private BuildingData _buildingData;

	private Color32 prevRedMaterialColor;

	private Color32 prevGreenMaterialColor;

	private Color32 prevBlueMaterialColor;

	private MaterialPropertyBlock matProp;

	private bool prevIsInSight;

	private string prevDefaultAnimationName;

	private UIGuildWarCorpsBattleInformation corpsBattleInformation;

	private Color32 prevBattleCorpsInformationColor;

	private int prevCurrentHP;

	private GameObject repairingEffect;

	private ulong prevOwnerChangeEffectGuildId;

	private bool isInSight
	{
		get
		{
			return false;
		}
	}

	private bool isInClientVision
	{
		get
		{
			return false;
		}
	}

	private bool isInCameraSight
	{
		get
		{
			return false;
		}
	}

	private string animationState
	{
		get
		{
			return null;
		}
	}

	private string prefabName
	{
		get
		{
			return null;
		}
	}

	private string prefabPath
	{
		get
		{
			return null;
		}
	}

	private int currentHP
	{
		get
		{
			return 0;
		}
	}

	private int maxHP
	{
		get
		{
			return 0;
		}
	}

	private bool repairing
	{
		get
		{
			return false;
		}
	}

	private long repairStartDate
	{
		get
		{
			return 0L;
		}
	}

	private long repairEndDate
	{
		get
		{
			return 0L;
		}
	}

	private ulong ownerGuildId
	{
		get
		{
			return 0uL;
		}
	}

	private int posX
	{
		get
		{
			return 0;
		}
	}

	private int posY
	{
		get
		{
			return 0;
		}
	}

	private string buildingName
	{
		get
		{
			return null;
		}
	}

	private Color32 color
	{
		get
		{
			return default(Color32);
		}
	}

	private Color32 redMaterialColor
	{
		get
		{
			return default(Color32);
		}
	}

	private Color32 greenMaterialColor
	{
		get
		{
			return default(Color32);
		}
	}

	private Color32 blueMaterialColor
	{
		get
		{
			return default(Color32);
		}
	}

	private void Start()
	{
	}

	public void AddEffectData(GuildWarEffectData effectData)
	{
	}

	private void UpdateEffect()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void Update()
	{
	}

	private void LateUpdate()
	{
	}

	public void RenderringInit(BuildingData buildingData)
	{
	}

	public override void RenderingUpdate()
	{
	}

	public void RenderringExit()
	{
	}

	private void InitBattleFieldObject()
	{
	}

	private void UpdateBattleFieldObject()
	{
	}

	private void ClearBattleFieldObject()
	{
	}

	private static tk2dSpriteAnimation GetGuildWarMapAnimation(string objectName, string objectIndex)
	{
		return null;
	}

	private void RefreshSprite(string prefabName)
	{
	}

	private void UpdateMaterialProps()
	{
	}

	private void InitPosition()
	{
	}

	private void UpdatePosition()
	{
	}

	private void InitVisible()
	{
	}

	private void UpdateVisible()
	{
	}

	private void ClearVisible()
	{
	}

	private void RemoveShowDamages()
	{
	}

	private void InitDefaultAnimationName()
	{
	}

	private void UpdateDefaultAnimationName()
	{
	}

	private void InitCorpsBattleInformation()
	{
	}

	private void UpdateCorpsBattleInformation()
	{
	}

	private void ClearCorpsBattleInformation()
	{
	}

	private void InitAnimationChangeEffect()
	{
	}

	private void UpdateAnimationChangeEffect()
	{
	}

	private void UpdateAnimationChangeEffectRepairing()
	{
	}

	private void UpdateAnimationChangeEffectHP()
	{
	}

	private void InitOwnerChangeEffect()
	{
	}

	private void UpdateOwnerChangeEffect()
	{
	}

	[DebuggerHidden]
	private IEnumerator ChangeBuildOwnerEffect()
	{
		return null;
	}

	private void InitPassiveDamages()
	{
	}

	private void UpdatePassiveDamages()
	{
	}
}
