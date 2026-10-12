using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class IsSleeping2d : ComponentAction<Rigidbody2D>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmEvent trueEvent;

		public FsmEvent falseEvent;

		public FsmBool store;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoIsSleeping()
		{
		}
	}
}
