using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class AddExplosionForce : ComponentAction<Rigidbody>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmVector3 center;

		[RequiredField]
		public FsmFloat force;

		[RequiredField]
		public FsmFloat radius;

		public FsmFloat upwardsModifier;

		public ForceMode forceMode;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnPreprocess()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnFixedUpdate()
		{
		}

		private void DoAddExplosionForce()
		{
		}
	}
}
