namespace HutongGames.PlayMaker.Actions
{
	public class Vector2Subtract : FsmStateAction
	{
		[RequiredField]
		public FsmVector2 vector2Variable;

		[RequiredField]
		public FsmVector2 subtractVector;

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
