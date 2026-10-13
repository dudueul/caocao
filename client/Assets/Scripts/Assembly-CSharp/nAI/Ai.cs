using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

namespace nAI
{
	public class Ai : MonoBehaviour
	{
		public enum Priority
		{
			Running = 0,
			ChoosingAction = 1,
			Yieldee = 2,
			Yielder = 3,
			Waiting = 4,
			NeedToRecover = 5,
			Complete = 6
		}

		public enum Entity
		{
			Unit = 0,
			SPawner = 1
		}

		public Priority priority;

		public Entity entity;

		private List<Action> m_actions;

		private Dictionary<string, int> m_scoreCaches;

		private Dictionary<IntVector2, TileScores> m_tileCaches;

		private List<TileScores> m_adjustedTileScore;

		public List<TileNode> pathToClosestEnemy;

		public List<TileNode> pathToGoal;

		public UnitData unitData;

		private int m_eventCmdSrl;

		private AI m_aiData;

		private int m_aiLevel;

		[SerializeField]
		private TargetInfo m_targetUnitInfo;

		public IntVector2 targetPosition;

		private UnitData m_targetUnit;

		public EnumData.aiMovableAreaType movableAreaType;

		public List<IntRect> movableRects;

		public List<IntVector2> movableGrids;

		public IntVector2 pivot;

		public int attackArea;

		public List<AIMovableAreaData> movableDatas;

		[SerializeField]
		private TargetInfo m_pivotUnitInfo;

		private UnitData m_pivotUnit;

		public int targetedScore;

		private Action m_secondaryAction;

		public int aiIndex;

		private Dictionary<EnumData.Skills, Func<AISkill, int>> m_skillPoints;

		private IntVector2 m_maxTeleportPos;

		public int eventCmdSrl
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public AI aiData
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public int aiLevel
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public AISet aiSet { get; private set; }

		public AITendency aiTendency { get; private set; }

		public AISkill aiSkill { get; private set; }

		public TargetInfo targetUnitInfo
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public UnitData targetUnit
		{
			get
			{
				return null;
			}
		}

		public TargetInfo pivotUnitInfo
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public UnitData pivotUnit
		{
			get
			{
				return null;
			}
		}

		public bool needHp
		{
			get
			{
				return false;
			}
		}

		public bool needMp
		{
			get
			{
				return false;
			}
		}

		public Action bestAction
		{
			get
			{
				return null;
			}
		}

		public Action secondaryAction
		{
			get
			{
				return null;
			}
		}

		public float yieldPoint
		{
			get
			{
				return 0f;
			}
		}

		public override string ToString()
		{
			return null;
		}

		private void OnStartAction()
		{
		}

		protected void OnFinishAction()
		{
		}

		private void SetAiData()
		{
		}

		private Action ExtractSecondaryAction()
		{
			return null;
		}

		private Action ExtractAction(Action from, TileNode tile)
		{
			return null;
		}

		private void RejectPosition(IntVector2 gridPos)
		{
		}

		public void SetAdjustedPoint(IntVector2 gridPos, float point)
		{
		}

		private void AdjustValues()
		{
		}

		protected void Start()
		{
		}

		public void SetTarget(TargetInfo targetUnitInfo, IntVector2 targetPosition)
		{
		}

		public void SetMovableArea(EnumData.aiMovableAreaType areaType, List<IntRect> rects, IntVector2 pivot, int attackArea, TargetInfo pivotUnitInfo)
		{
		}

		public void SetMovableAreaBonusScore(int score, EnumData.aiMovableAreaType areaType, List<IntRect> rects, IntVector2 pivot, int attackArea, TargetInfo pivotUnitInfo)
		{
		}

		public void SetTargetedScore(int score)
		{
		}

		protected void ComputeActions()
		{
		}

		private void SetAIParts()
		{
		}

		public TileScores GetTileScore(IntVector2 gridPos)
		{
			return default(TileScores);
		}

		private bool NeedToRecover()
		{
			return false;
		}

		public bool WillStay()
		{
			return false;
		}

		private void ListUpActions()
		{
		}

		private void ListUpMoves()
		{
		}

		private void ListUpAttacks()
		{
		}

		private void ListUpSkills()
		{
		}

		private void _ListUpSkill(EnumData.Skills skillId, Func<UnitData, bool> targetFilter, QuickPathFinderContext context)
		{
		}

		private void _ListUpNonTargetSkill(EnumData.Skills skillId, QuickPathFinderContext context)
		{
		}

		private bool CanUseSkillOnAi(EnumData.Skills skillId)
		{
			return false;
		}

		private bool CanUseSkillOnState(EnumData.Skills skillId)
		{
			return false;
		}

		private void ListUpItems()
		{
		}

		private void _ListUp(nAI.Action.ActionType actionType, EnumData.Skills skillId, int itemId, int attackRangeType, Func<UnitData, bool> targetFilter, QuickPathFinderContext context)
		{
		}

		private TileNode GetTileNodeIfMovable(IntVector2 pos, QuickPathFinderContext context)
		{
			return null;
		}

		private bool CheckMovableArea(IntVector2 pos)
		{
			return false;
		}

		private void InitLists()
		{
		}

		public void Reset()
		{
		}

		private void FindBestAction()
		{
		}

		public void FindBestVipAttackAction(nAI.Action.ActionType actionType, EnumData.Skills skillId, List<UnitData> targets, QuickPathFinderContext context)
		{
		}

		[DebuggerHidden]
		public IEnumerator Process()
		{
			return null;
		}

		private void _ListUpOfFireReturn(Func<UnitData, bool> targetFilter, QuickPathFinderContext context)
		{
		}

		private int GetMovableBonusScore(IntVector2 gridPos)
		{
			return 0;
		}

		private void OnDestroy()
		{
		}
	}
}
