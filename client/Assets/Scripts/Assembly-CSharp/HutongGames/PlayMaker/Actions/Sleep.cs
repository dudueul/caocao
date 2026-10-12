using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class Sleep : ComponentAction<Rigidbody>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSleep()
		{
		}
	}
}
