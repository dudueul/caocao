using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using Caocao;
using UnityEngine;

namespace Battle
{
	public class ArrowRain : SceneSingleton<ArrowRain>
	{
		private const EnumData.Conditions LAST_CONDITION = EnumData.Conditions.DEADLY_WOUND;

		private const float ARROW_SCREEN_ASPECT = 2.36f;

		private const float IN_AREA_HEIGHT_RATE = 1.328f;

		private const string prefs_show_damage_area = "btn_show_damage_area";

		private List<int> turnInfo;

		private List<IntRect> leftAreas;

		private List<IntRect> rightAreas;

		private List<int> damages;

		private List<int> mpDamages;

		private int curIdx;

		private int lastTurn;

		private IntRect[] areas;

		private IntRect[] prevAreas;

		private Vector2[] shootPos;

		private Vector2[] camPos;

		private Vector2[] shootCamPos;

		private int prevTurn;

		private BattleCameraController cam;

		private List<UnitData> damageUnits;

		private List<EventTwinkleMarking> twinkleMarkers;

		private IntRect nullArea;

		private Vector2 centerCamPos;

		private float fullWidthZoomout;

		private float arrowZoomout;

		private Coroutine doArrowRainCoroutine;

		private Coroutine doShowAlertAreaCoroutine;

		private float onceSmoothTwinkleTime;

		public bool showArea
		{
			get
			{
				return false;
			}
			private set
			{
			}
		}

		private bool finished
		{
			get
			{
				return false;
			}
		}

		private IntRect leftArea
		{
			get
			{
				return default(IntRect);
			}
		}

		private IntRect rightArea
		{
			get
			{
				return default(IntRect);
			}
		}

		private Vector2 leftPos
		{
			get
			{
				return default(Vector2);
			}
		}

		private Vector2 rightPos
		{
			get
			{
				return default(Vector2);
			}
		}

		public int damage
		{
			get
			{
				return 0;
			}
		}

		public int mpDamage
		{
			get
			{
				return 0;
			}
		}

		public bool doingArrowRain { get; private set; }

		public void SetArrowRain(List<int> turnInfo, List<IntRect> leftAreas, List<IntRect> rightAreas, List<int> damages, List<int> mpDamages, int leftArcherUnitId, IntVector2 leftArcherPos, UnitDirection leftArcherDir, int rightArcherUnitId, IntVector2 rightArcherPos, UnitDirection rightArcherDir)
		{
		}

		public bool IsTurnChanged()
		{
			return false;
		}

		public bool IsArrowRainTurn()
		{
			return false;
		}

		public bool IsStarted()
		{
			return false;
		}

		public bool IsLastTurn(int turn)
		{
			return false;
		}

		public Coroutine DoArrowRain(UILabelLocalized labelDesc)
		{
			return null;
		}

		[DebuggerHidden]
		private IEnumerator _DoArrowRain(UILabelLocalized labelDesc)
		{
			return null;
		}

		public void SkipTurn(int turn)
		{
		}

		public void DoOnlyDamage()
		{
		}

		private void DoOnlyDamageOnUnit(int curTurn, UnitData unit, int damage, int mpDamage)
		{
		}

		private void CreateAreaCurrentAlert()
		{
		}

		private void ShowShootEffect(Vector2 pos, bool isLeft)
		{
		}

		private void ShowHitEffect(UnitData unit, bool isLeft)
		{
		}

		private void ShowEffect(Transform parent, Vector2 pos, string poolName, string prefabName)
		{
		}

		[DebuggerHidden]
		private IEnumerator YieldReturnMinTime(Coroutine coroutine, float minTime)
		{
			return null;
		}

		public void ToggleShowAlertArea()
		{
		}

		public void CreateAndShowAlertArea()
		{
		}

		public Coroutine DoShowAlertArea()
		{
			return null;
		}

		[DebuggerHidden]
		private IEnumerator _DoShowAlertArea()
		{
			return null;
		}

		[DebuggerHidden]
		private IEnumerator TwinkleHoldOnFunc(GameObject go)
		{
			return null;
		}

		[DebuggerHidden]
		private IEnumerator TwinkleSmoothHoldOnFunc(GameObject go)
		{
			return null;
		}

		[DebuggerHidden]
		private IEnumerator TwinkleFunc(GameObject go)
		{
			return null;
		}

		[DebuggerHidden]
		private IEnumerator TwinkleOnOffFunc(GameObject go)
		{
			return null;
		}

		[DebuggerHidden]
		private IEnumerator ShowAlertArea(Func<GameObject, IEnumerator> func = null)
		{
			return null;
		}

		private EventTwinkleMarking CreateTwinkleMarkinig(int x, int y)
		{
			return null;
		}

		private Coroutine DamageUnit(int curTurn, UnitData unit, int damage, int mpDamage)
		{
			return null;
		}

		private void OnBattleEnd()
		{
		}
	}
}
