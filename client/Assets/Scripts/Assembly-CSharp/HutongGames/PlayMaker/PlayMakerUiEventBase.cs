using System;
using System.Collections.Generic;
using UnityEngine;

namespace HutongGames.PlayMaker
{
	public abstract class PlayMakerUiEventBase : MonoBehaviour
	{
		public List<PlayMakerFSM> targetFsms;

		[NonSerialized]
		protected bool initialized;

		public void AddTargetFsm(PlayMakerFSM fsm)
		{
		}

		private bool TargetsFsm(PlayMakerFSM fsm)
		{
			return false;
		}

		protected void OnEnable()
		{
		}

		public void PreProcess()
		{
		}

		protected virtual void Initialize()
		{
		}

		protected void SendEvent(FsmEvent fsmEvent)
		{
		}
	}
}
