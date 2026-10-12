using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class Flicker : ComponentAction<Renderer>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat frequency;

		public FsmFloat amountOn;

		public bool rendererOnly;

		public bool realTime;

		private float startTime;

		private float timer;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
