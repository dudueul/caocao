using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class Vector3LowPassFilter : FsmStateAction
	{
		[RequiredField]
		public FsmVector3 vector3Variable;

		public FsmFloat filteringFactor;

		private Vector3 filteredVector;

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
