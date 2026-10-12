namespace HutongGames.PlayMaker.Actions
{
	public class Vector3RotateTowards : FsmStateAction
	{
		[RequiredField]
		public FsmVector3 currentDirection;

		[RequiredField]
		public FsmVector3 targetDirection;

		[RequiredField]
		public FsmFloat rotateSpeed;

		[RequiredField]
		public FsmFloat maxMagnitude;

		public override void Reset()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
