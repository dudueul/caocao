using System.Collections;
using System.Diagnostics;
using Caocao;
using UnityEngine;

public class DuelCmdStartBattle : DuelCmdBase
{
	private GameObject SingleCombatPrefab;

	private bool needToCreateUnit;

	private UnitInfo ownUnitInfo;

	private UnitInfo enemyUnitInfo;

	private TargetInfo ownUnitTargetInfo;

	private TargetInfo enemyUnitTargetInfo;

	private int ownKingdom;

	private int enemyKingdom;

	private float sceneDurage;

	private float fadeInOutDurage;

	private float fadeOffset;

	private int duelIntroId;

	private int duelMapId;

	private int duelBGM;

	private GameObject Owner
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private GameObject Enemy
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public override void SetData(object[] args)
	{
	}

	[DebuggerHidden]
	public override IEnumerator ProcessCommand()
	{
		return null;
	}

	public override void OnCommandStarted()
	{
	}

	public override void OnCommandFinished()
	{
	}

	private GameObject GetWeatherPrefab(Caocao.Weather weather)
	{
		return null;
	}
}
