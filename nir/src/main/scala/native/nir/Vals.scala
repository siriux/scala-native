package native
package nir

sealed abstract class Val {
  final def ty: Type = this match {
    case Val.None            => Type.None
    case Val.Zero            => ???
    case Val.True
       | Val.False           => Type.Bool
    case Val.I8(_)           => Type.I8
    case Val.I16(_)          => Type.I16
    case Val.I32(_)          => Type.I32
    case Val.I64(_)          => Type.I64
    case Val.F32(_)          => Type.F32
    case Val.F64(_)          => Type.F64
    case Val.Struct(ty, _)   => ty
    case Val.Array(ty, vals) => Type.Array(ty, vals.length)
    case Val.Name(ty, _)     => ty
    case Val.Null            => Type.Null
    case Val.Unit            => Type.Unit
  }
}
object Val {
  final case object None                                    extends Val
  final case object Zero                                    extends Val
  final case object True                                    extends Val
  final case object False                                   extends Val
  final case class I8    (value: Byte)                      extends Val
  final case class I16   (value: Short)                     extends Val
  final case class I32   (value: Int)                       extends Val
  final case class I64   (value: Long)                      extends Val
  final case class F32   (value: Float)                     extends Val
  final case class F64   (value: Double)                    extends Val
  final case class Struct(structty: Type, values: Seq[Val]) extends Val
  final case class Array (elemty: Type, values: Seq[Val])   extends Val
  final case class Name  (namety: Type, name: nir.Name)     extends Val

  //scala
  final case object Null extends Val
  final case object Unit extends Val
}