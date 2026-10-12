using System;
using UnityEngine;
using UnityEngine.Scripting;

namespace HutongGames.PlayMaker
{
	[Preserve]
	public class FsmProcessor
	{
		public static void OnPreprocess(PlayMakerFSM fsm)
		{
		}

		private static void HandleUiEvent<T>(PlayMakerFSM fsm, UiEvents uiEvent) where T : PlayMakerUiEventBase
		{
		}

		private static void AddUiEventHandler<T>(PlayMakerFSM fsm) where T : PlayMakerUiEventBase
		{
		}

		private static bool AddEventHandlerComponent(PlayMakerFSM fsm, Type type)
		{
			return false;
		}

		public static PlayMakerProxyBase GetEventHandlerComponent(GameObject go, Type type)
		{
			return null;
		}
	}
}
