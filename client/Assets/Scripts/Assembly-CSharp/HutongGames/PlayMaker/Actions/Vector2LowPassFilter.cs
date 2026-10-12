using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class Vector2LowPassFilter : FsmStateAction
	{
		[RequiredField]
		public FsmVector2 vector2Variable;

		public FsmFloat filteringFactor;

		private Vector2 filteredVector;

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
