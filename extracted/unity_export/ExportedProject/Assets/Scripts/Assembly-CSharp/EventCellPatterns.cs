using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using UnityEngine;

public class EventCellPatterns
{
	[StructLayout((LayoutKind)0, Size = 16)]
	public struct EventPattern_UnitInfo
	{
		public int nUnitIndex;

		public string sNickName;

		public string NickName
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

	[StructLayout((LayoutKind)0, Size = 8)]
	public struct EventPattern_VariableCheckBase
	{
		public bool bEnable;

		private int nVarialble;

		public int Variable
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

	public struct EventPattern_VariableCheck
	{
		public List<int> lVarialble1;

		public List<int> lVarialble2;

		public void AddVariable1(int nVariable)
		{
		}

		public void AddVariable2(int nVariable)
		{
		}
	}

	public struct EventPattern_DeckSetting
	{
		public bool forceEnableCaoCao;

		private int nBattleUnits;

		public Dictionary<int, string> lPickedUp;

		public Dictionary<int, string> lLimitedOut;

		public int BattleUnits
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public bool AddItem(string sName, int nUnitID, bool bIsPickedUp)
		{
			return false;
		}

		private bool _FindUnitItem(int nUnitID)
		{
			return false;
		}
	}

	public struct EventPattern_Selection
	{
		public int nUnitIndex;

		public List<string> lSelection;

		public string _question;

		public int nSaveFlagID;

		public string question
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public void AddSeletion(string sSelection)
		{
		}
	}

	[StructLayout((LayoutKind)0, Size = 40)]
	public struct EventPattern_Dialog_Base
	{
		public int faceType;

		public int typeParam;

		public EventPattern_UnitInfo unitInfo;

		public string sDialog;

		public bool bWaiting;

		public int direction;

		public string Dialog
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

	public struct EventPattern_Dialog
	{
		public List<EventPattern_Dialog_Base> lDialogList;

		public void AddDialog(EventPattern_Dialog_Base dialogBase)
		{
		}
	}

	public struct EventPattern_DialogEx
	{
		public Dictionary<string, int> lUnitIndexList;

		public string Dialog;

		public int typeParam;

		public int faceType;

		public bool Waiting;

		public bool AddSpeaker(int nUnitIndex, string sNickName)
		{
			return false;
		}
	}

	[StructLayout((LayoutKind)0, Size = 24)]
	public struct EventPattern_UnitProperty
	{
		public EventPattern_UnitInfo unitInfo;

		public bool bFlag;
	}

	[StructLayout((LayoutKind)0, Size = 12)]
	public struct EventPattern_MoveToPoint
	{
		public Vector2 vPos;

		public float fSpeed;
	}

	[StructLayout((LayoutKind)0, Size = 24)]
	public struct EventPattern_AddObject
	{
		public Vector2 vPos;

		public int nState;

		public bool bOption1;

		public bool bOption2;

		public int tacticsType;
	}

	[StructLayout((LayoutKind)0, Size = 20)]
	public struct EventPattern_UnitFace
	{
		public Vector2 vPos;

		public int nUnitIndex;

		public float delay;

		public int faceType;
	}

	[StructLayout((LayoutKind)0, Size = 32)]
	public struct EventPattern_UnitEntryArea
	{
		public Vector2 vStartPos;

		public Vector2 vEndPos;

		public EventPattern_UnitInfo unitInfo;
	}

	[StructLayout((LayoutKind)0, Size = 32)]
	public struct EventPattern_MapProperty
	{
		public bool bOption1;

		public bool bOption2;

		public bool bOption3;

		public bool bWaiting;

		public int nNumber;

		public string sDescription;

		public string Description
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

	[StructLayout((LayoutKind)0, Size = 56)]
	public struct EventPattern_AdjoinUnits
	{
		public EventPattern_UnitInfo unitInfo1;

		public EventPattern_UnitInfo unitInfo2;

		public bool bNeighboring;

		public int nDirect;

		public bool bDirecting;

		public bool bDelayBeforeAct;

		public bool bDelayAfterAct;
	}

	[StructLayout((LayoutKind)0, Size = 32)]
	public struct EventPattern_AppearUnit
	{
		public EventPattern_UnitInfo unitInfo;

		public Vector2 vPos;

		public int nDirect;

		public int nActionID;
	}

	[StructLayout((LayoutKind)0, Size = 56)]
	public struct EventPattern_UnitSpawnInfo
	{
		public int nBFIndex;

		public int nGroupNo;

		public int nDirect;

		public int nFriendNo;

		public int nCashedNo;

		public EnumData.BattleOwnTeamDeckType nOptimalJob;

		public bool bHide;

		public Vector2 vPos;

		public int tacticsType;

		public int aiIndex;

		public int targetIndex;

		public Vector2 targetPos;
	}

	[StructLayout((LayoutKind)0, Size = 88)]
	public struct EventPattern_DestroyUnit
	{
		public int nExtent;

		public EventPattern_UnitInfo unitInfo;

		public EventPattern_UnitInfo targetInfo;

		public Vector2 vStartPos;

		public Vector2 vEndPos;

		public Vector2 vTargetPos;

		public int nGroupIndex;

		public int nAIIndex;

		public bool bDeath;

		public int nBFIndex;

		public bool showRetreatMsg;
	}

	[StructLayout((LayoutKind)0, Size = 48)]
	public struct EventPattern_UnitMove
	{
		public int nModeIndex;

		public EventPattern_UnitInfo unitInfo;

		public int nBFIndex;

		public Vector2 vPos;

		public int nDirect;

		public bool bHide;

		public bool bWaiting;
	}

	[StructLayout((LayoutKind)0, Size = 24)]
	public struct EventPattern_UnitDirection
	{
		public EventPattern_UnitInfo unitInfo;

		public int nActionID;

		public int nDirect;
	}

	[StructLayout((LayoutKind)0, Size = 48)]
	public struct EventPattern_UnitAction
	{
		public EventPattern_UnitInfo unitInfo;

		public EventPattern_UnitInfo targetInfo;

		public int nActionClassID;

		public int nBattleActionID;

		public bool bDelayBeforeAct;

		public bool bDelayAfterAct;
	}

	[StructLayout((LayoutKind)0, Size = 32)]
	public struct EventPattern_UnitShapeChange
	{
		public EventPattern_UnitInfo unitInfo;

		public EventPattern_UnitInfo targetInfo;
	}

	[StructLayout((LayoutKind)0, Size = 24)]
	public struct EventPattern_UnitGrade
	{
		public EventPattern_UnitInfo unitInfo;

		public int nParam;
	}

	[StructLayout((LayoutKind)0, Size = 8)]
	public struct EventPattern_UnitJob
	{
		public int nUnitIndex;

		public int nJobIndex;
	}

	[StructLayout((LayoutKind)0, Size = 32)]
	public struct EventPattern_UnitAttribution
	{
		public EventPattern_UnitInfo unitInfo;

		public int nAttributiton;

		public int nCalSignIndex;

		public int nParam;
	}

	[StructLayout((LayoutKind)0, Size = 12)]
	public struct EventPattern_Goal
	{
		public int nGoalIndex;

		public int nParam;

		public int nCalSignIndex;
	}

	public struct Event_AddUnit
	{
		public List<int> unitLIst;

		public int unitIndex;

		public int nJoining;

		public int nStep;

		public int nCalSignIndex;

		public bool useShortAni;

		public void AddUnit(int unitId)
		{
		}
	}

	[StructLayout((LayoutKind)0, Size = 48)]
	public struct Event_UseableItem
	{
		public int nItemIndex;

		public int nItemGradeIndex;

		public int nWeaponIndex;

		public int nWeaponGradeIndex;

		public int nInstrumentIndex;

		public int nInstrumentGradeIndex;

		public int nExtraItem;

		public int nExtraItemGradeIndex;

		public int nUnitIndex;

		public int nSaveFlagID;

		public int nItemQuantity;

		public bool bChangeView;
	}

	[Serializable]
	[StructLayout((LayoutKind)0, Size = 8)]
	public struct Event_Trophy
	{
		public int nItemID;

		public int nGrade;
	}

	public struct Event_BattleTrophy
	{
		public int nExcessGold;

		public bool bFinal;

		public bool scenarioBlock01;

		public bool scenarioBlock02;

		public List<Event_Trophy> lTrophyList;

		public void AddItem(Event_Trophy sTrophy)
		{
		}
	}

	[StructLayout((LayoutKind)0, Size = 20)]
	public struct Event_LoadMap
	{
		public EnumData.MapType eMapType;

		public int nLinkedIndexOuter;

		public int nLinkedIndexHuge;

		public int nLinkedIndexInner;

		public int nLinkedIndexBattle;

		public int LinkedIndex
		{
			get
			{
				return 0;
			}
		}
	}

	[StructLayout((LayoutKind)0, Size = 32)]
	public struct Event_MapUnitCount
	{
		public int nGroupIndex;

		public int nCalSignIndex;

		public int nTargetAreaIndex;

		public int nParam;

		public Vector2 vStartPos;

		public Vector2 vEndPos;
	}

	[StructLayout((LayoutKind)0, Size = 20)]
	public struct Event_ImportantArea
	{
		public Vector2 vStartPos;

		public Vector2 vEndPos;

		public bool bOption;

		public Event_ImportantArea(Vector2 startPos, Vector2 endPos, bool settled)
		{
			vStartPos = default(Vector2);
			vEndPos = default(Vector2);
			bOption = false;
		}
	}

	[StructLayout((LayoutKind)0, Size = 40)]
	public struct Event_MapVariableQuantity
	{
		public bool bFlag1;

		public bool bFlag2;

		public int nCount;

		public int nGrade;

		public int nEnymyValor;

		public int nEnemyLeaderID;

		public int nOwnValor;

		public int nOwnLeaderID;

		public int nWeatherIndex;

		public int nWeatherEventIndex;
	}

	[StructLayout((LayoutKind)0, Size = 64)]
	public struct Event_UnitJoiningCondition
	{
		public bool bIsRelief;

		public bool bIsHide;

		public EventPattern_UnitInfo unitInfo;

		public int nTargetUnitIndex;

		public Vector2 vPos;

		public Vector2 vTargetPos;

		public int nDirect;

		public int nGradeIndex;

		public int nQuality;

		public int nAIIndex;

		public int tacticsType;
	}

	public struct Event_UnitJoiningConditionList
	{
		public List<Event_UnitJoiningCondition> lUnitJoiningCondition;

		public void AddCondition(Event_UnitJoiningCondition sCondition)
		{
		}
	}

	[StructLayout((LayoutKind)0, Size = 72)]
	public struct EventPattern_UnitPropertyEx
	{
		public int nModeIndex;

		public int nBFIndex;

		public int nBFIndexFrom;

		public int nBFIndexTo;

		public EventPattern_UnitInfo unitInfo;

		public Vector2 vStartPos;

		public Vector2 vEndPos;

		public int nGroupIndex;

		public int nAttributiton;

		public int nChanging;

		public int nStateIndex;

		public int nHP;

		public int nMP;
	}

	public struct EventPattern_TourneyUnit
	{
		public bool bIsOurTroop;

		public bool bIsEnemyTroop;

		public bool bDelay;

		public List<string> lDialog;

		public int nTourneyActionIndex;

		public void AddDialog(string sDialog)
		{
		}
	}

	[StructLayout((LayoutKind)0, Size = 64)]
	public struct EventPattern_TourneyAttack
	{
		public bool bIsOurTroop;

		public bool bCriticalAttack;

		public bool bIsHitable;

		public int nTourneyActionIndex1;

		public int nTourneyActionIndex2;

		public int groupIndex1;

		public int groupIndex2;

		public EventPattern_UnitInfo unitInfo1;

		public EventPattern_UnitInfo unitInfo2;
	}

	[StructLayout((LayoutKind)0, Size = 16)]
	public struct EventPattern_MagicSkill
	{
		public bool bOption;

		public Vector2 vPos;

		public int nMagicIndex;
	}

	[StructLayout((LayoutKind)0, Size = 16)]
	public struct EventPattern_VictoryCondition
	{
		public string sWinCondition;

		public string sLoseCondition;

		public string WinCondition
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public string LoseCondition
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

	[StructLayout((LayoutKind)0, Size = 8)]
	public struct EventPattern_TourneyTarget
	{
		public int ownUnitID;

		public int enemyUnitID;
	}

	[StructLayout((LayoutKind)0, Size = 24)]
	public struct EventPattern_SelectObject
	{
		public string sObjectName;

		public string sUnitName;

		public int onOff;

		public bool delay;

		public string ObjectName
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public string UnitName
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

	[StructLayout((LayoutKind)0, Size = 36)]
	public struct EventPattern_BattleFieldObject
	{
		public int ObjectIndex;

		public int AttributeValue;

		public Vector2 vPos;

		public int CreationIndex;

		public bool IsFocus;

		public bool Waiting;

		public int objectType;

		public int tacticsType;
	}

	public struct EventPattern_TutorialPrefabLoad
	{
		public string prefabName;

		public List<string> relativeUIPaths;

		public bool destroyAnywhereTouched;

		public void AddUIPath(string path)
		{
		}

		public void DeleteUIPath(string path)
		{
		}
	}

	[StructLayout((LayoutKind)0, Size = 8)]
	public struct EventPattern_CheckControlFlowStatus
	{
		public string status;
	}

	[StructLayout((LayoutKind)0, Size = 8)]
	public struct EventPattern_SetUICompStatus
	{
		public string status;
	}

	public int m_nVariable;

	public float m_fFloating;

	private int m_nPositiveVariable1;

	private int m_nPositiveVariable2;

	public EventPattern_UnitInfo m_sPattern_UnitInfo;

	public EventPattern_VariableCheckBase m_sPattern_VariableCheckBase;

	public EventPattern_VariableCheck m_sPattern_VariableCheck;

	public EventPattern_DeckSetting m_sPattern_DeckSetting;

	public EventPattern_Selection m_sPattern_Selection;

	public EventPattern_Dialog_Base m_sPattern_Dialog_Base;

	public EventPattern_Dialog m_sPattern_Dialog;

	public EventPattern_DialogEx m_sPattern_DialogEx;

	public EventPattern_UnitProperty m_sPattern_Property;

	public EventPattern_MoveToPoint m_sPattern_MoveToPoint;

	public EventPattern_AddObject m_sPattern_AddObject;

	public EventPattern_UnitFace m_sPattern_UnitFace;

	public EventPattern_UnitEntryArea m_sPattern_UnitEntryArea;

	public EventPattern_MapProperty m_sPattern_MapProperty;

	public EventPattern_AdjoinUnits m_sPattern_AdjoinUnits;

	public EventPattern_AppearUnit m_sPattern_AppearUnit;

	public EventPattern_UnitSpawnInfo m_sPattern_UnitSpawnInfo;

	public EventPattern_DestroyUnit m_sPattern_DestroyUnit;

	public EventPattern_UnitMove m_sPattern_UnitMove;

	public EventPattern_UnitDirection m_sPattern_UnitDirection;

	public EventPattern_UnitAction m_sPattern_UnitAction;

	public EventPattern_UnitShapeChange m_sPattern_UnitShapeChange;

	public EventPattern_UnitGrade m_sPattern_UnitGrade;

	public EventPattern_UnitJob m_sPattern_UnitJob;

	public EventPattern_UnitAttribution m_sPattern_UnitAttribution;

	public EventPattern_Goal m_sPattern_Goal;

	public Event_AddUnit m_sPattern_AddUnit;

	public Event_UseableItem m_sPattern_UseableItem;

	public Event_Trophy m_sPattern_Trophy;

	public Event_BattleTrophy m_sPattern_BattleTrophy;

	public Event_LoadMap m_sPattern_LoadMap;

	public Event_MapUnitCount m_sPattern_MapUnitCount;

	public Event_ImportantArea m_sPattern_ImportantArea;

	public Event_MapVariableQuantity m_sPattern_MapVariableQuantity;

	public Event_UnitJoiningCondition m_sPattern_UnitJoiningCondition;

	public Event_UnitJoiningConditionList m_sPattern_UnitJoiningConditionList;

	public EventPattern_UnitPropertyEx m_sPattern_PropertyEx;

	public EventPattern_TourneyUnit m_sPattern_TourneyUnit;

	public EventPattern_TourneyAttack m_sPattern_TourneyAttack;

	public EventPattern_MagicSkill m_sPattern_MagicSkill;

	public EventPattern_VictoryCondition m_sPattern_VictoryCondition;

	public EventPattern_TourneyTarget m_sPattern_TourneyTartget;

	public EventPattern_SelectObject m_sPattern_SelectObject;

	public EventPattern_BattleFieldObject m_sPattern_BattleFieldObject;

	public EventPattern_TutorialPrefabLoad m_sPattern_TutorialPrefabLoad;

	public EventPattern_CheckControlFlowStatus m_sPattern_checkCFStatus;

	public EventPattern_SetUICompStatus m_sPattern_setUiCompStatus;

	public int PositiveVariable1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int PositiveVariable2
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
