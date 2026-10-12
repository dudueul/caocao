using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;
using nAI;

namespace BattlePlay
{
	public class BattlePlayManager : Singleton<BattlePlayManager>
	{
		private BattlePlayData m_battlePlayData;

		private Dictionary<int, BattleControl> m_waitingControls;

		private Coroutine m_aiCoroutine;

		public BattleControl currentBattleControl;

		private BattleControl lasttBattleControl;

		public float lastBattleStateCheck;

		private float lastNetworkReachabilityCheck;

		private const int lastBattleStateCheckTerm = 5;

		public float continueLoadingPercentage;

		public static FairRule fairRule;

		public BattlePlayData battlePlayData
		{
			get
			{
				return null;
			}
		}

		public void InitBattleData(ulong hostUid, JsonData battlePlayData)
		{
		}

		public void InitControls()
		{
		}

		public void InitBattlePlayData()
		{
		}

		public void SetBattleData(BattlePlayData data)
		{
		}

		public BattleUser GetBattleUser(ulong uid)
		{
			return null;
		}

		public bool EmitControl(ControlType type, ControlFlow.statusType status, UnitData controlUnit, UnitData targetUnit, int tileIdx, int skillId, int itemId, IntVector2 mouseGridPos)
		{
			return false;
		}

		public void EmitControl(Action action)
		{
		}

		public void EmitCompleteAction(UnitData controlUnit, bool ignorePendingState = false)
		{
		}

		public void EmitSticker(int stickerId)
		{
		}

		public void EmitUserSpell()
		{
		}

		public void AddControl(BattleControl battleControl)
		{
		}

		public void AddMissedControls(BattleControl[] battleControls)
		{
		}

		public void AddSticker(BattleSticker battleSticker)
		{
		}

		public void ChangeTurnTime(List<int> turns)
		{
		}

		public void UpdateLastControlTurns(UnitData unit)
		{
		}

		public void ResetUnitTurnTime(ulong uid, bool resetToMax = false)
		{
		}

		public void ResetUnitTurnTimeByCheat(int[] turns)
		{
		}

		private void CheckControl(int idx)
		{
		}

		public void EndMultiPlayAIControl()
		{
		}

		private void Update()
		{
		}

		private void UpdateControl()
		{
		}

		private void UpdateSticker()
		{
		}

		private bool CheckStickerCondition(BattleSticker sticker)
		{
			return false;
		}

		private bool CheckStatus()
		{
			return false;
		}

		private bool CheckControllingUnit(BattleControl battleControl)
		{
			return false;
		}

		private void CheckBattleStateOnTimeOver()
		{
		}

		[DebuggerHidden]
		private IEnumerator ShowReplayBattleEndPopup()
		{
			return null;
		}

		public void StartCheckBattleStepOnWatch()
		{
		}

		[DebuggerHidden]
		private IEnumerator CheckBattleStepOnWatch()
		{
			return null;
		}

		public void FinishWatchLoading()
		{
		}

		[DebuggerHidden]
		private IEnumerator FinishLoadingAndLeaveWatch()
		{
			return null;
		}

		private void SetFinishLoading(bool instantly = false)
		{
		}
	}
}
