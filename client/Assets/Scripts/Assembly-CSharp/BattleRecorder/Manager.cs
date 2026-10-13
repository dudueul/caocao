using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Security.Cryptography;
using Caocao;
using ICSharpCode.SharpZipLib.Zip;

namespace BattleRecorder
{
	public static class Manager
	{
		private const string m_headerFileName = "cconhead";

		private const string m_historyFileName = "cconhist";

		private const int m_curSaveFileVersion = 55;

		public static History history;

		private static bool ms_inBattle;

		private static Action ms_curAction;

		private static ControlFlow.statusType ms_prevStatus;

		private static bool ms_headerWritten;

		private const int m_initBufferSize = 262144;

		private const int m_checkBufferSize = 40960;

		private static byte[] ms_buffer;

		private static FileStream ms_headerFs;

		private static CryptoStream ms_headerCs;

		private static OptimizedBinaryReader ms_headerBr;

		private static System.Action OnTimelineChange__BackingField;

		private static byte[] key;

		public static event System.Action OnTimelineChange
		{
			add
			{
			}
			remove
			{
			}
		}

		public static RijndaelManaged GetAes128()
		{
			return null;
		}

		public static bool ValidateSaveFile()
		{
			return false;
		}

		public static void OpenHeaderFile()
		{
		}

		public static void CloseHeaderFile()
		{
		}

		public static void DeleteSaveFile()
		{
		}

		public static bool LoadBattleSetting()
		{
			return false;
		}

		private static void LoadHistory()
		{
		}

		public static bool ReadBattleSrl()
		{
			return false;
		}

		public static void SaveStartBattle()
		{
		}

		public static void SaveStartBattleWithoutHistory()
		{
		}

		public static void Save(bool withHistory = true)
		{
		}

		private static void ResizeBuffer(int fileSize)
		{
		}

		public static void Delete()
		{
		}

		public static Action GetLastAction()
		{
			return null;
		}

		public static List<Tuple<TimelineMarker, UnitActionComplete>> GetTimelines()
		{
			return null;
		}

		public static Tuple<TimelineMarker, UnitActionComplete> GetTimeline(int sequence)
		{
			return null;
		}

		public static bool CanRevertHand()
		{
			return false;
		}

		public static bool CanRevertTurn()
		{
			return false;
		}

		public static void OnBattleStart()
		{
		}

		public static void OnBattleFinish()
		{
		}

		private static void OnControlFlowStatusChange(ControlFlow.statusType status)
		{
		}

		public static void OnFinishTurn()
		{
		}

		public static void OnUnitActionComplete(UnitData unitData)
		{
		}

		public static void Record(Action action)
		{
		}

		public static void RecordMovement(UnitData unitData, IntVector2 fromPos, UnitDirection fromDir)
		{
		}

		public static void RecordChangeDirection(UnitData unitData, UnitDirection toDir)
		{
		}

		public static void RecordDeath(UnitData unitData, bool dead)
		{
		}

		public static void RecordHide(UnitData unitData)
		{
		}

		public static ChangeUnitSide RecordChangeUnitSide(UnitData unitData, UnitSide unitSide)
		{
			return null;
		}

		public static void RecordMapObjectStatus(int mapObjectId, IntVector2 gridPos, int prevStatusId, int statusId)
		{
		}

		public static void RecordAIChange(UnitData unitData, int aiType, TargetInfo targetUnitInfo, IntVector2 targetPos)
		{
		}

		public static void RecordAIMovableAreaChange(UnitData unitData, int areaType, List<IntRect> coords, IntVector2 pivot, int attackArea, TargetInfo targetUnitInfo)
		{
		}

		public static void RecordAITargetedScoreChange(UnitData unitData, int score)
		{
		}

		public static void RecordWeatherChange(Caocao.Weather prevWeather, Caocao.Weather newWeather)
		{
		}

		public static void RecordEventSceneClose(int eventScene, bool closed)
		{
		}

		public static void RecordEventMenuOnChange(bool value)
		{
		}

		public static void RecordEventVariableChange(int index, int value, int prevValue)
		{
		}

		public static void RecordEventTriggerOccured(int index)
		{
		}

		public static void RecordEventExecution(EventCmdBase eventCmd)
		{
		}

		public static void RecordBattleMission(BattleMission mission)
		{
		}

		public static void RecordCaptureBattleMapObjects(UnitData unitData, UnitSide prevSide)
		{
		}

		public static void RecordFireIgnition(int id, IntVector2 pos, bool ignition)
		{
		}

		public static void CancelMovement()
		{
		}

		public static void ChangeTimeline(Action marker)
		{
		}

		[DebuggerHidden]
		public static IEnumerator LoadHistoryAndApply()
		{
			return null;
		}

		public static void OnOpenUI()
		{
		}

		public static void OnCancelUndo()
		{
		}

		public static void OnConfirmUndo(bool emergency = false)
		{
		}

		public static void RevertOneTurn()
		{
		}

		public static int NumUndoActions(Action marker)
		{
			return 0;
		}

		public static int UndoCost()
		{
			return 0;
		}

		public static int UndoTurnCost()
		{
			return 0;
		}

		public static void PayUndoCost(System.Action successCallback)
		{
		}

		public static void PayUndoTurnCost(System.Action onFail = null)
		{
		}

		public static void SendSaveFileToServer()
		{
		}

		private static void WriteStream(ZipOutputStream zos, Stream exceptionStream, string entryName)
		{
		}
	}
}
