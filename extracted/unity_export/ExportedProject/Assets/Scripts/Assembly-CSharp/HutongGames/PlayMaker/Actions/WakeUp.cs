using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class WakeUp : ComponentAction<Rigidbody>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoWakeUp()
		{
		}
	}
}
