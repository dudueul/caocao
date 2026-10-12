using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class QuaternionLowPassFilter : QuaternionBaseAction
	{
		[RequiredField]
		public FsmQuaternion quaternionVariable;

		public FsmFloat filteringFactor;

		private Quaternion filteredQuaternion;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		public override void OnLateUpdate()
		{
		}

		public override void OnFixedUpdate()
		{
		}

		private void DoQuatLowPassFilter()
		{
		}
	}
}
