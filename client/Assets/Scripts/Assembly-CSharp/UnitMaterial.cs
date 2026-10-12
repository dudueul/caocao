using Caocao;
using UnityEngine;

public class UnitMaterial : MonoBehaviour
{
	private static int? m_mainTexId;

	private static int? m_alphaDyeTexId;

	private static int? m_dyeAlphaTexId;

	private static int? m_alphaTexId;

	private static int? m_dyeTexId;

	private static int? m_redColorId;

	private static int? m_greenColorId;

	private static int? m_blueColorId;

	private static Color32 ownRed;

	private static Color32 ownGreen;

	private static Color32 ownBlue;

	private static Color32 allyRed;

	private static Color32 allyGreen;

	private static Color32 allyBlue;

	private static Color32 allyRed2;

	private static Color32 allyGreen2;

	private static Color32 allyBlue2;

	private static Color32 enemyRed;

	private static Color32 enemyGreen;

	private static Color32 enemyBlue;

	private static Color32 enemyAllyRed;

	private static Color32 enemyAllyGreen;

	private static Color32 enemyAllyBlue;

	private static Color32 noneGray;

	public static Color32[][][] teamColors;

	public FilterMode filterMode;

	private static bool ms_init;

	private static int mainTexId
	{
		get
		{
			return 0;
		}
	}

	private static int alphaDyeTexId
	{
		get
		{
			return 0;
		}
	}

	private static int dyeAlphaTexId
	{
		get
		{
			return 0;
		}
	}

	private static int alphaTexId
	{
		get
		{
			return 0;
		}
	}

	private static int dyeTexId
	{
		get
		{
			return 0;
		}
	}

	private static int redColorId
	{
		get
		{
			return 0;
		}
	}

	private static int greenColorId
	{
		get
		{
			return 0;
		}
	}

	private static int blueColorId
	{
		get
		{
			return 0;
		}
	}

	public void OnUnitChanged(int unitId, int lookUnitId, int skinId, int jobTypeId, int jobLevel, UnitSide enemyType)
	{
	}

	public void OnUnitDirectionChanged(int unitId, int lookUnitId, int skinId, int jobTypeId, int jobLevel, UnitSide unitSide, UnitDirection faceDirection)
	{
	}

	public void ChangeDirection(UnitDirection direction, string action = "Ready")
	{
	}

	public void ChangeBattleDirection(UnitDirection direction, string action = "Ready")
	{
	}

	public void ChangeUIDirection(UnitDirection direction, string action = "Ready")
	{
	}

	private static void InitStatics()
	{
	}

	private void Awake()
	{
	}

	private void GetTeamColors(bool isBattleUnit, int mtrlIndex, Unit unit, UnitSide enemyType, out Color32 color1, out Color32 color2, out Color32 color3)
	{
		color1 = default(Color32);
		color2 = default(Color32);
		color3 = default(Color32);
	}

	private void CreateMaterials(int unitId, int skinId, int jobTypeId, int jobLevel, UnitSide enemyType, FilterMode filterMode)
	{
	}

	public static void UpdateMaterialColor(EnumData.UnitMaterialType prevMtrl, EnumData.UnitMaterialType newMtrl)
	{
	}

	private static int ConvertColor32(Color32 src)
	{
		return 0;
	}
}
