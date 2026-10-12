using System.Collections.Generic;
using GuildWar;
using UnityEngine;

public class GuildWarRendererCorps : GuildWarRendererObject
{
	private CorpsData _corpsData;

	private string prevMaterialPrefabPrefixName;

	private string prevMaterialHpRatioIndexString;

	private string prevMaterialShaderName;

	private string prevPrefabPrefixName;

	private string prevHpRatioIndexString;

	private Color32 prevRedMaterialColor;

	private Color32 prevGreenMaterialColor;

	private Color32 prevBlueMaterialColor;

	private MaterialPropertyBlock matProp;

	private HexDirection prevForwardDirection;

	private int prevPosX;

	private int prevPosY;

	private bool prevIsInSight;

	private string prevDefaultAnimationName;

	private UIGuildWarCorpsBattleInformation corpsBattleInformation;

	private int prevLevel;

	private Dictionary<int, GameObject> blockIcons;

	private static Vector3 s_iconStartPoint;

	private bool isInSight
	{
		get
		{
			return false;
		}
	}

	private string prefabPrefixName
	{
		get
		{
			return null;
		}
	}

	private string hpRatioIndexString
	{
		get
		{
			return null;
		}
	}

	private string defaultAnimationName
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

	private HexDirection forwardDirection
	{
		get
		{
			return HexDirection.RIGHT;
		}
	}

	private bool isSiegeWeapon
	{
		get
		{
			return false;
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

	private string nationName
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

	private string jobEmblem
	{
		get
		{
			return null;
		}
	}

	private int level
	{
		get
		{
			return 0;
		}
	}

	private List<PassiveMarkerType> passiveMarkerTypes
	{
		get
		{
			return null;
		}
	}

	private void Start()
	{
	}

	private void OnDestroy()
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

	private void StartAnimationOnce(string animationName)
	{
	}

	public void RenderringInit(CorpsData corpsData)
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

	private void ProcessCreateCorpsEffect()
	{
	}

	private void UpdateBattleFieldObject()
	{
	}

	private void ClearBattleFieldObject()
	{
	}

	private static tk2dSpriteAnimation GetForceAnimation(string prefabPrefixName, string hpRatioIndexString)
	{
		return null;
	}

	private void RefreshSprite(string prefabPrefixName, string hpRatioIndexString)
	{
	}

	private static Material GetForceMaterial(string prefabPrefixName, string hpRatioIndexString)
	{
		return null;
	}

	private void UpdateMaterialProps()
	{
	}

	private void InitTransform()
	{
	}

	private void UpdateTransform()
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

	private void InitPassiveDamages()
	{
	}

	private void UpdatePassiveDamages()
	{
	}

	private void InitLevelUpEffect()
	{
	}

	private void UpdateLevelUpEffect()
	{
	}

	private void InitBlockIcons()
	{
	}

	private void ClearBlockIcons()
	{
	}

	private void UpdateBlockIcons()
	{
	}

	private bool IsCorpsPassiveActive(int passiveId)
	{
		return false;
	}
}
