using System.Collections;
using System.Diagnostics;
using Caocao;
using UnityEngine;

public class BattleTeleport : Move
{
	private const float BATTLE_TELEPORT_CAMERA_SPEED = 0.5f;

	private EnumData.Skills m_skillId;

	private UnitFSMQueue m_unitFSMQueue;

	private MoveParam m_moveParam;

	private IntVector2 m_befGridPos;

	private UnitDirection m_befDirection;

	private const float gap = 2.1599998f;

	private bool m_isMoveCameraComplete;

	public void StartAnimation(EnumData.Skills skillId, IntVector2 toGridPos)
	{
	}

	[DebuggerHidden]
	private IEnumerator Animate(IntVector2 toGridPos)
	{
		return null;
	}

	private static bool NeedToMoveCamera(IntVector2 toGridPos, Camera camera)
	{
		return false;
	}

	private void MoveCamera(IntVector2 toGridPos, Camera cam)
	{
	}

	private void MoveCameraCompleted()
	{
	}

	private void UnitTeleportCompleted()
	{
	}

	private void PlaySkillSpeechSound(Skill skillData)
	{
	}
}
