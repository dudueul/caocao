namespace HutongGames.PlayMaker.Actions
{
	public class Vector2Multiply : FsmStateAction
	{
		[RequiredField]
		public FsmVector2 vector2Variable;

		[RequiredField]
		public FsmFloat multiplyBy;

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
	}
}
