using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class GetKeyDown : FsmStateAction
	{
		[RequiredField]
		public KeyCode key;

		public FsmEvent sendEvent;

		public FsmBool storeResult;

		public override void Reset()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
